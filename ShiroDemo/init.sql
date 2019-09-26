-- 权限表 --
create table permission (
    permission_id int(11) not null auto_increment comment '权限id',
    permission_name varchar(100) not null default '' comment '权限名称',
    url varchar(255) default '' comment '权限路径',
    primary key (permission_id)
)ENGINE = InnoDB default character  = utf8;

insert into permission values (default ,'1','add','');
insert into permission values (default ,'2','delete','');
insert into permission values (default ,'3','edit','');
insert into permission values (default ,'4','query','');

-- 用户表 --
create table user(
    user_id int(11) not null auto_increment comment '用户id',
    username varchar(100) not null comment '用户名',
    password varchar(255) not null comment '用户密码',
     primary key (user_id)
)ENGINE = InnoDB default character  = utf8;

insert into user values (default ,'admin','123');
insert into user values (default ,'user','123');

-- 角色表 --
create table role(
    role_id int(11) not null auto_increment comment '角色id',
    role_name varchar(255) not null default '' comment '角色名称',
    primary key (role_id)
)ENGINE = InnoDB default character  = utf8;

insert into role values (default ,'admin');
insert into role values (default ,'customer');

-- 权限角色关系表 --
create table permission_role(
    role_id int(11) not null,
    permission_id int(11) not null,
    key idx_role_id (role_id),
    key ids_permission_id (permission_id)
)ENGINE = InnoDB default character  = utf8;

insert into permission_role values  ('1' ,'1');
insert into permission_role values  ('1' ,'2');
insert into permission_role values  ('1' ,'3');
insert into permission_role values  ('1' ,'4');
insert into permission_role values  ('2' ,'1');
insert into permission_role values  ('2' ,'1');

-- 用户角色关系表 --
create table user_role(
    user_id int(11) not null,
    permission_id int(11) not null,
    key idx_user_id (user_id),
    key ids_permission_id (permission_id)
)ENGINE = InnoDB default character  = utf8;

insert into user_role values (1,1);
insert into user_role values (2,2);
