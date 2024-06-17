use gamevibe;

DROP TABLE IF EXISTS post;
CREATE TABLE post
(
    id          bigint auto_increment comment 'id' PRIMARY KEY,
    user_id     bigint                                     NOT NULL COMMENT '用户id',
    title       varchar(128)                               NOT NULL COMMENT '标题',
    type        varchar(128)                               NOT NULL COMMENT '游戏类别',
    images      varchar(1024)                              NULL COMMENT '图片，多个图片以,隔开',
    content     varchar(1024)                              NOT NULL COMMENT '帖子内容',
    comments    int(10) UNSIGNED DEFAULT 0                 NOT NULL COMMENT '评论数量',
    post_time   datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '帖子发布时间',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint          DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '帖子表' collate = utf8mb4_unicode_ci;

INSERT INTO post(user_id,title,type,images,content,comments) VALUES (1,'宝可梦对战入门','宝可梦系列','https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%9D%E5%8F%AF%E6%A2%A6&step_word=&lid=9550131787730797085&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3961126115,158086461&os=3254334051,1214124167&simid=3961126115,158086461&pn=16&rn=1&di=7360350738658099201&ln=1870&fr=&fmq=1717918837755_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fp3.toutiaoimg.com%2Fpgc-image%2Fc0998f07af504c44b64a39ecce06e1a8~noop.image%3F_iz%3D58558%26from%3Darticle.pc_detail%26x-expires%3D1676603270%26xature%3DlH94qXzoV3wUKJt7b2THCO5U0%252BA%253D&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsMiw2LDQsNSw4LDcsOQ%3D%3D','帖子内容',0);
INSERT INTO post(user_id,title,type,images,content,comments) VALUES (2,'幻兽帕鲁樱岛版本速报!6月27日更!','幻兽帕鲁','https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%9D%E5%8F%AF%E6%A2%A6&step_word=&lid=9550131787730797085&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3961126115,158086461&os=3254334051,1214124167&simid=3961126115,158086461&pn=16&rn=1&di=7360350738658099201&ln=1870&fr=&fmq=1717918837755_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fp3.toutiaoimg.com%2Fpgc-image%2Fc0998f07af504c44b64a39ecce06e1a8~noop.image%3F_iz%3D58558%26from%3Darticle.pc_detail%26x-expires%3D1676603270%26xature%3DlH94qXzoV3wUKJt7b2THCO5U0%252BA%253D&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsMiw2LDQsNSw4LDcsOQ%3D%3D','帖子内容',2);


DROP TABLE IF EXISTS post_comment;
CREATE TABLE post_comment
(
    id          bigint auto_increment comment 'id' PRIMARY KEY,
    user_id     bigint                             NOT NULL COMMENT '用户id',
    post_id     bigint                             NOT NULL COMMENT '帖子id',
    content     varchar(1024)                      NOT NULL COMMENT '评论内容',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '帖子评论表' collate = utf8mb4_unicode_ci;


# CREATE TABLE IF NOT EXISTS game_info
# (
#     id           bigint auto_increment comment 'id' PRIMARY KEY,
#     name         varchar(128)                       NOT NULL COMMENT '游戏名称',
#     description  varchar(1024)                      NULL COMMENT '游戏介绍',
#     size         double                             NULL COMMENT '游戏大小',
#     images       varchar(1024)                      NULL COMMENT '图片',
#     publisher    varchar(128)                       NULL COMMENT '发布商',
#     release_date DATE                               NULL COMMENT '游戏发布时间',
#     update_date  DATE                               NULL COMMENT '游戏更新时间',
#     create_time  datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
#     update_time  datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
#     is_delete    tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
# ) comment '游戏信息表' collate = utf8mb4_unicode_ci;
#
#
# CREATE TABLE IF NOT EXISTS game_type
# (
#     id          bigint auto_increment comment 'id' PRIMARY KEY,
#     name        varchar(128)                       NOT NULL COMMENT '游戏类型名称',
#     create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
#     update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
#     is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
# ) comment '游戏类型表' collate = utf8mb4_unicode_ci;
#
#
# CREATE TABLE IF NOT EXISTS game_info_type
# (
#     id          bigint auto_increment comment 'id' PRIMARY KEY,
#     game_id     bigint                             NOT NULL COMMENT '游戏id',
#     type_id     bigint                             NOT NULL COMMENT '游戏类型id',
#     create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
#     update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
#     is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
# ) comment '游戏类型关联表' collate = utf8mb4_unicode_ci;
#
#
# CREATE TABLE IF NOT EXISTS game_comment
# (
#     id          bigint auto_increment comment 'id' PRIMARY KEY,
#     user_id     bigint                             NOT NULL COMMENT '用户',
#     score       tinyint(1)                         NOT NULL COMMENT '评分',
#     content     varchar(1000)                      NULL COMMENT '评论内容',
#     create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
#     update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
#     is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
# ) comment '游戏评论表' collate = utf8mb4_unicode_ci;
#
# CREATE TABLE IF NOT EXISTS game_comment
# (
#     id          bigint auto_increment comment 'id' PRIMARY KEY,
#     user_id     bigint                             NOT NULL COMMENT '用户',
#     score       tinyint(1)                         NOT NULL COMMENT '评分',
#     content     varchar(1000)                      NULL COMMENT '评论内容',
#     create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
#     update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
#     is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
# ) comment '游戏评论表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id          bigint AUTO_INCREMENT COMMENT '个人主页id' PRIMARY KEY,
    user_id     varchar(255)                               NOT NULL COMMENT '用户id',
    nick_name   varchar(16)      DEFAULT '手机用户'         NOT NULL COMMENT '用户昵称',
    avatar      varchar(255)                               NOT NULL COMMENT '用户头像',
    intro       varchar(32)      DEFAULT ''                NOT NULL COMMENT '用户简介',
    ip_addr     varchar(128)       DEFAULT '未知'           NOT NULL COMMENT 'ip地址',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint          DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '用户信息表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS post_like;
CREATE TABLE post_like
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                               NOT NULL COMMENT '用户id',
    post_id     bigint                                     NOT NULL COMMENT '帖子id',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint          DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未点赞,1为已点赞)'
) comment '帖子点赞表' collate = utf8mb4_unicode_ci;

ALTER TABLE post_like ADD UNIQUE INDEX index_uid_pid(user_id, post_id);

DROP TABLE IF EXISTS post_collect;
CREATE TABLE post_collect
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                               NOT NULL COMMENT '用户id',
    post_id     bigint                                     NOT NULL COMMENT '帖子id',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint          DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未收藏,1为已收藏)'
) comment '帖子收藏表' collate = utf8mb4_unicode_ci;

ALTER TABLE post_collect ADD UNIQUE INDEX index_uid_pid(user_id, post_id);

DROP TABLE IF EXISTS focus_user;
CREATE TABLE focus_user
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                               NOT NULL COMMENT '用户id',
    focused_id  varchar(255)                               NOT NULL COMMENT '被关注的用户id',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint          DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未关注,1为已关注)'
) comment '关注用户表' collate = utf8mb4_unicode_ci;

ALTER TABLE focus_user ADD UNIQUE INDEX index_uid_fid(user_id, focused_id);

INSERT INTO post_like (user_id, post_id) VALUES
('43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac', 1),
('43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac', 2);






