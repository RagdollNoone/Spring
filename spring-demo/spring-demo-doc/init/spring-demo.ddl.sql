create database if not exists Spring;

use Spring;

-- 商品表
create table if not exists product_info (
    id integer not null auto_increment comment '主键id',
    name varchar(64) not null comment '商品名称',
    family varchar(32) not null comment '类别',
    stock integer not null comment '库存',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
create table if not exists user_info (
    id integer not null auto_increment comment '主键id',
    name varchar(64) not null comment '姓名',
    age varchar(4) not null comment '年龄',
    sex varchar(1) not null comment '性别',
    telephone varchar(16) not null comment '手机号',
    address varchar(128) not null comment '地址',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
create table if not exists order_info (
    id integer not null auto_increment comment '主键id',
    order_no varchar(64) not null comment '订单号',
    user_id integer not null comment '用户id',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    constraint order_no_unique unique (order_no),
    constraint user_id_fk foreign key (user_id) references user_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单明细表
create table if not exists order_detail_info (
    id integer not null auto_increment comment '主键id',
    order_no integer not null comment '订单号',
    product_id integer not null comment '商品号',
    product_num integer not null comment '商品数量',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    constraint order_no_fk foreign key (order_no) references order_info(id),
    constraint product_id_fk foreign key (product_id) references product_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;