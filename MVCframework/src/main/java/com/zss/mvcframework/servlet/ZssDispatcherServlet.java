package com.zss.mvcframework.servlet;

import com.zss.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZSS
 * @date 2019/10/26 16:19
 * @description disoatcherServlet
 */
public class ZssDispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private List<String> classNameList = new ArrayList<>();

    private Map<String, Object> ioc = new HashMap<>();

    // private Map<String, Method> handlerMapping = new HashMap<>();

    private List<Handler> handlerMapping = new ArrayList<Handler>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2、扫描所有相关的类
        doScanner(properties.getProperty("scanPackage"));

        // 3、初始化所有相关Class的实例，并且保存到IOC容器众
        doInstance();

        // 4、自动化的依赖注入
        doAutowired();

        // 5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("ZSS MVC Framework is init success");
    }

    /**
     * 加载配置文件
     */
    private void doLoadConfig(String location) {
        // 获取配置文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 扫描所有相关的类
     */
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        if (url == null) {
            return;
        }
        File classesDir = new File(url.getFile());
        for (File file : classesDir.listFiles()) {
            // 如果这是个文件夹，则递归扫描下一层文件
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNameList.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }

    }

    /**
     * 初始化所有相关Class的实例，并且保存到IOC容器众
     */
    private void doInstance() {
        if (classNameList.isEmpty()) {
            return;
        }
        for (String className : classNameList) {
            try {
                Class<?> clazz = Class.forName(className);
                /*
                 * 进行实例化，原则问题
                 * 判断，不是所有的牛奶都叫特仑苏
                 * 只有加了@ZssController和@ZssService的类才给它初始化
                 */
                // 扫描@ZssController
                if (clazz.isAnnotationPresent(ZssController.class)) {
                    /*
                     * beanName beanId
                     * 1、默认采用类名的首字母小写
                     * 2、如果自己定义了名字的话，优先使用自己定义的名字
                     * 3、根据类型匹配，利用实现类的接口名作为key
                     */
                    String beanName = lowerFirst(clazz.getSimpleName());
                    try {
                        ioc.put(beanName, clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // 扫描@ZssService
                } else if (clazz.isAnnotationPresent(ZssService.class)) {
                    ZssService service = clazz.getAnnotation(ZssService.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirst(clazz.getSimpleName());
                    }

                    Object instance = null;

                    try {
                        instance = clazz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();

                    }
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 自动化的依赖注入
     */
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 在Spring里面没有隐私
            // 只认@ZssAutowried
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ZssAutowired.class)) {
                    continue;
                }
                ZssAutowired autowired = field.getAnnotation(ZssAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                // 设置暴力访问
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }

            }

        }

    }

    /**
     * 初始化HandlerMapping
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(ZssController.class)) {
                continue;
            }
            String url = "";
            if (clazz.isAnnotationPresent(ZssRequestMapping.class)) {
                ZssRequestMapping requestMapping = clazz.getAnnotation(ZssRequestMapping.class);
                url = requestMapping.value();
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                // 没有加RequestMapping注解的直接忽略
                if (!method.isAnnotationPresent(ZssRequestMapping.class)) {
                    continue;
                }
                // 映射URL
                ZssRequestMapping requestMapping = method.getAnnotation(ZssRequestMapping.class);
                String regex = ("/" + url + requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(entry.getValue(), method, pattern));
                System.out.println("Mapping " + regex + "," + method);
            }

        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Handler handler = getHandler(req);
            if (handler == null) {
                // 如果没有匹配上，返回404错误
                resp.getWriter().write("404 Not Found");
                return;
            }

            // 获取方法的参数列表
            Class<?>[] paramTypes = handler.method.getParameterTypes();

            // 保存所有需要自动赋值的参数值
            Object[] paramValues = new Object[paramTypes.length];

            Map<String, String[]> params = req.getParameterMap();
            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");

                // 如果找到匹配的对象，则开始填充参数值
                if (!handler.paramIndexMapping.containsKey(param.getKey())) {
                    continue;
                }
                int index = handler.paramIndexMapping.get(param.getKey());
                paramValues[index] = convert(paramTypes[index], value);
            }

            // 设置方法中的request和response对象
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;

            handler.method.invoke(handler.controller, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return String
     */
    private String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private Handler getHandler(HttpServletRequest req) throws Exception {
        if (handlerMapping.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);
            // 如果没有匹配上就继续下一个匹配
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }
        return null;
    }

    private Object convert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        return value;
    }


    private class Handler {
        protected Object controller;       // 保存方法对应的实例
        protected Method method;            // 保存映射的方法
        protected Pattern pattern;
        protected Map<String, Integer> paramIndexMapping;    // 参数顺序

        private Handler(Object controller, Method method, Pattern pattern) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;
            paramIndexMapping = new HashMap<String, Integer>();
        }

        private void putParamIndexMapping(Method method) {
            // 提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof ZssRequestParam) {
                        String paramName = ((ZssRequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramIndexMapping.put(paramName, i);
                        }
                    }
                }
            }

            // 提取方法中的request和reponse参数
            Class<?>[] paramsTypes = method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length; i++) {
                Class<?> type = paramsTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(), i);
                }
            }
        }
    }

}
