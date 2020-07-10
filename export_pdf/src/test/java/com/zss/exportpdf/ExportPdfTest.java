package com.zss.exportpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/9 15:18
 * @desc 导出Pdf文件 - 测试
 */
public class ExportPdfTest extends BaseTest {

    /**
     * 简单打印 - 测试
     * 问题：文件创建成功，但是打开失败
     * 解决：writer.open();
     */
    @Test
    public void exportPdfFileSimpleTest() {
        String path = "C:/Users/12711/Desktop/ExportPdf/text.pdf";
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.addTitle("EXPORT TEST");
            document.open();
            document.add(new Paragraph("Hello PDF"));
            writer.open();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        System.out.println("打印成功！！！");
    }

    /**
     * 在pdf中添加表格 - test
     */
    @Test
    public void exportPdfFileWithTableTest() {
        String path = "C:/Users/12711/Desktop/ExportPdf/text2.pdf";
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.addTitle("EXPORT TEST");
            document.open();

            /*
                设置表格属性
             */
            // 生成一个两列的表格
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell;
            int size = 15;
            cell = new PdfPCell(new Phrase("one"));
            // 设置表格高度
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("two"));
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("three"));
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("four"));
            cell.setFixedHeight(size);
            table.addCell(cell);

            document.add(table);
            writer.open();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    @Test
    public void exportPdfTestComplete() throws Exception {
        // 创建document
        Document document = new Document(PageSize.A4);
        // 设置要导出的文件名
        String path = "C:/Users/12711/Desktop/ExportPdf/text3.pdf";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        document.open();

        // 获取字体文件目录
        String fontDir = Objects.requireNonNull(this.getClass().getClassLoader().getResource("font")).getFile();
        // 注册字体文字
        XMLWorkerFontProvider provider = new XMLWorkerFontProvider(fontDir);
        // 设置中文字体
        BaseFont baseFont = BaseFont.createFont("font/STXINGKA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font12 = new Font(baseFont);

        // 页眉，可以是双层或者更多，取决于放置的坐标
        PdfContentByte cb = writer.getDirectContent();
        // 页眉左上
        Phrase leftUp = new Phrase("页眉左上", font12);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, leftUp, document.left(), document.top() + 13, 0);
        // 页眉左下
        Phrase leftDown = new Phrase("页眉左下", font12);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, leftDown, document.left(), document.top(), 0);
        // 页眉右上
        Phrase rightUp = new Phrase("页眉右上", font12);
        float rightUpWidthPoint = baseFont.getWidthPoint(rightUp.getContent(), 12);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rightUp, document.right() - rightUpWidthPoint, document.top() + 13, 0);
        // 页眉右下
        Phrase rightDown = new Phrase("页眉右下", font12);
        float rightDownWidthPoint = baseFont.getWidthPoint(rightDown.getContent(), 12);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rightDown, document.right() - rightDownWidthPoint, document.top(), 0);

        // 下划线
        PdfContentByte canvas = writer.getDirectContent();
        CMYKColor magentaColor = new CMYKColor(1.f, 1.f, 1.f, 1.f);
        canvas.setColorStroke(magentaColor);
        canvas.moveTo(document.left(), document.top() - 4);
        canvas.lineTo(document.right(), document.top() - 4);
        canvas.closePathStroke();

        // 标题
        Font h1 = new Font(baseFont, 20, Font.BOLD);
        Paragraph paragraph = new Paragraph("标题Hello", h1);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // 正文标题
        Font h3 = new Font(baseFont, 14, Font.BOLD);
        Paragraph paragraphH1 = new Paragraph("正文标题1", h3);
        document.add(paragraphH1);

        //正文1
        Paragraph paragraphText1 = new Paragraph("你能做到最好，你能做到最好，你能做到最好，你能做到最好，你能做到最好，你能做到最好，你能做到最好。", font12);
        document.add(paragraphText1);

        //正文2，html标签内容
        String content = "<div class=\"overflow-hidden\"><div class=\"editContent\"><p><br>eah, you could be the greatest<br>你会成为最伟大的人<br>You can be the best<br>你能做到最好<br>You can be the King Kong banging on your chest<br>你能像金刚一样自信满满的敲打胸脯<br>You could beat the world<br>你可以征服全世界<br>You could beat the war<br>能够赢得一切战争<br>You could talk to God, go banging on his door<br>甚至能够与神对话 去敲打他的门<br>You can throw your hands up<br>你能自信的举起双手<br>You can be the clock<br>你可以与时间抗争<br>You can move a mountain<br>你有移山之力<br>You can break rocks<br>你能击碎岩石<br>You can be a master<br>你可以成为命运主宰<br>Don't wait for luck<br>无需等待运气垂青<br>Dedicate yourself and you can find yourself<br>放手一搏后你会恍然发现<br>Standing in the hall of fame<br>你已身处名人堂之中</p></div></div>";
        // html转换成普通文字
        org.jsoup.nodes.Document contentDoc = Jsoup.parseBodyFragment(content);
        org.jsoup.nodes.Document.OutputSettings outputSettings = new org.jsoup.nodes.Document.OutputSettings();
        outputSettings.syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        contentDoc.outputSettings(outputSettings);
        String parseHtml = contentDoc.outerHtml();
        // 这的font-family不支持汉字
        InputStream cssIs = new ByteArrayInputStream("* {font-family: fangsong;}".getBytes(StandardCharsets.UTF_8));

        // 第四个参数是html中的css文件输入流
        // 第五个参数似乎字体提供者，使用系统默认支持的字体时，可以不传
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(parseHtml.getBytes()), cssIs, provider);

        //页脚
        PdfContentByte cj = writer.getDirectContent();
        Phrase left = new Phrase("页脚左侧", font12);
        ColumnText.showTextAligned(cj, Element.ALIGN_LEFT, left, document.left(), document.bottom(), 0);
        Phrase right = new Phrase("页脚右侧", font12);
        final float rightp3WidthPoint = baseFont.getWidthPoint(right.getContent(), 12);
        ColumnText.showTextAligned(cj, Element.ALIGN_LEFT, right, document.right() - rightp3WidthPoint, document.bottom(), 0);

        //关闭
        document.close();
        System.out.println("打印成功！！！");
    }


}
