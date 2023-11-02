use tuling;

DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor` (
`id` int(11) NOT NULL,
`name` varchar(45) DEFAULT NULL,
`update_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `actor` (`id`, `name`, `update_time`) VALUES (1,'a','2017-12-22 15:27:18'), (2,'b','2017-12-22 15:27:18'), (3,'c','2017-12-22 15:27:18');

DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(10) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `film` (`id`, `name`) VALUES (3,'film0'),(1,'film1'),(2,'film2');

DROP TABLE IF EXISTS `film_actor`;
CREATE TABLE `film_actor` (
`id` int(11) NOT NULL,
`film_id` int(11) NOT NULL,
`actor_id` int(11) NOT NULL,
`remark` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `idx_film_actor_id` (`film_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 简单使用
select * from actor;
explain select *  from actor;

-- explain extended
select * from film where id = 1;
explain extended select * from film where id = 1; -- TODO: 没有通过
show warnings; -- TODO: 没有成功

-- explain字段解析
-- id 字段
-- 几个select 有几个id id越大 执行优先级越高

-- select_type 字段
-- SIMPLE表示简单查询 不包含子查询union
explain select * from film where id = 2;

set session optimizer_switch = 'derived_merge=off'; -- 关闭衍生表的合并优化
-- PRIMARY类型 主查询
-- DERIVED类型 from后面的查询语句
-- SUBQUERY类型 from前面的查询语句
explain select (select 1 from actor where id = 1) from (select * from film where id = 1) der;
set session optimizer_switch = 'derived_merge=on'; -- 还原配置

-- union类型
explain select 1 union all select 1;

-- table 字段
-- 表示explain正在访问那张表
explain select min(id) from film; -- 优化后 不需要访问表 table字段为空

-- partitions 字段

-- type 字段(关联类型或访问类型)
-- system > const > eq_ref > ref > range > index > all

-- system const
explain select * from (select * from film where id = 1) tmp;

-- eq_ref
explain select * from film_actor left join film on film_actor.film_id = film.id;

-- ref
explain select * from film where name = 'film1';
explain select film_id from film left join film_actor on film.id = film_actor.film_id;

-- range
explain select * from actor where id > 1;

-- index 索引使用的是idx_name
explain select * from film;

-- all
explain select * from actor;

-- possible_keys 字段
-- 可能会被使用的索引

-- key列
-- 被使用的索引

-- key_len
-- 索引类型的字节数
explain select * from film_actor where film_id = 2;
-- key_len 计算规则
-- char(n) 数字或字母n个字节 汉字3n个字节
-- varchar(n) 数字或字母(n+2)个字节 汉字(3n+2)个字节 2个字节存字符串长度
-- tinyint 1个字节
-- smallint 2个字节
-- int 4个字节
-- bigint 8个字节
-- date 3个字节
-- timestamp 4个字节
-- datetime 8个字节
-- 字段允许为空 需要额外一个字节存储该信息
