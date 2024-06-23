use gamevibe;

DROP TABLE IF EXISTS post;
CREATE TABLE post
(
    id          bigint auto_increment comment 'id' PRIMARY KEY,
    user_id     varchar(255)                               NOT NULL COMMENT '用户id',
    title       varchar(128)                               NOT NULL COMMENT '标题',
    type        bigint                                     NOT NULL COMMENT '关联游戏',
    images      varchar(1024)                              NULL COMMENT '图片，多个图片以,隔开',
    content     varchar(1024)                              NOT NULL COMMENT '帖子内容',
    location    varchar(32)                                NOT NULL COMMENT '帖子发布地点',
    comments    int(10) UNSIGNED DEFAULT 0                 NOT NULL COMMENT '评论数量',
    likes       int(10) UNSIGNED DEFAULT 0                 NOT NULL COMMENT '点赞数量',
    favours     int(10) UNSIGNED DEFAULT 0                 NOT NULL COMMENT '收藏数量',
    pv          bigint           DEFAULT 0                 NOT NULL comment '阅读量',
    post_time   datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '帖子发布时间',
    create_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint          DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '帖子表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS news;
CREATE TABLE news
(
    id          bigint auto_increment comment 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    title       varchar(128)                       NOT NULL COMMENT '标题',
    type        bigint                             NOT NULL COMMENT '关联游戏',
    images      varchar(1024)                      NULL COMMENT '图片，多个图片以,隔开',
    content     varchar(1024)                      NOT NULL COMMENT '资讯内容',
    post_time   datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '资讯发布时间',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '官方资讯表' collate = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS post_comment;
CREATE TABLE post_comment
(
    id          bigint auto_increment comment 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    post_id     bigint                             NOT NULL COMMENT '帖子id',
    content     varchar(1024)                      NOT NULL COMMENT '评论内容',
    location    varchar(32)                        NOT NULL COMMENT '评论地点',
    post_name   datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '评论发布时间',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '帖子评论表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id     varchar(255) PRIMARY KEY COMMENT '用户id',
    id          bigint UNIQUE AUTO_INCREMENT COMMENT '个人主页id',
    intro       varchar(32)  DEFAULT '专属于你的gamevibe!'                   NOT NULL COMMENT '用户简介',
    avatar      varchar(255) DEFAULT 'https://cdn.casbin.org/img/casbin.svg' NOT NULL COMMENT '用户头像',
    nick_name   varchar(16)  DEFAULT '手机用户'                              NOT NULL COMMENT '用户昵称',
    ip_addr     varchar(128) DEFAULT '未知'                                  NOT NULL COMMENT 'ip地址',
    focus_count int(10)      DEFAULT 0                                       NOT NULL COMMENT '关注数量',
    fans_count  int(10)      DEFAULT 0                                       NOT NULL COMMENT '粉丝数量',
    create_time datetime     DEFAULT CURRENT_TIMESTAMP                       NOT NULL COMMENT '创建时间',
    update_time datetime     DEFAULT CURRENT_TIMESTAMP                       NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint      DEFAULT 0                                       NOT NULL COMMENT '是否删除'
) comment '用户信息表' collate = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS post_like;
CREATE TABLE post_like
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    post_id     bigint                             NOT NULL COMMENT '帖子id',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint  DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未点赞,1为已点赞)'
) comment '帖子点赞表' collate = utf8mb4_unicode_ci;

ALTER TABLE post_like
    ADD UNIQUE INDEX index_uid_pid (user_id, post_id);

DROP TABLE IF EXISTS post_collect;
CREATE TABLE post_collect
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    post_id     bigint                             NOT NULL COMMENT '帖子id',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint  DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未收藏,1为已收藏)'
) comment '帖子收藏表' collate = utf8mb4_unicode_ci;

ALTER TABLE post_collect
    ADD UNIQUE INDEX index_uid_pid (user_id, post_id);

DROP TABLE IF EXISTS focus_user;
CREATE TABLE focus_user
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    focused_id  varchar(255)                       NOT NULL COMMENT '被关注的用户id',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    state       tinyint  DEFAULT 1                 NOT NULL COMMENT '是否关注(0为未关注,1为已关注)'
) comment '关注用户表' collate = utf8mb4_unicode_ci;

ALTER TABLE focus_user
    ADD UNIQUE INDEX index_uid_fid (user_id, focused_id);
ALTER TABLE focus_user
    ADD INDEX index_fid (focused_id);

INSERT INTO post_like (user_id, post_id)
VALUES ('43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac', 1),
       ('43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac', 2);

DROP TABLE IF EXISTS game;
CREATE TABLE game
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    `name`      varchar(255)                               NOT NULL COMMENT '游戏名称',
    images      json                                       NOT NULL COMMENT '游戏图片',
    type        varchar(255)                               NOT NULL COMMENT '游戏类型',
    score       decimal(3, 1) DEFAULT 0.0                  NOT NULL COMMENT '游戏评分',
    intro       varchar(512)  DEFAULT '该游戏暂未提供简介' NOT NULL COMMENT '游戏简介',
    create_time datetime      DEFAULT CURRENT_TIMESTAMP    NOT NULL COMMENT '创建时间',
    update_time datetime      DEFAULT CURRENT_TIMESTAMP    NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint       DEFAULT 0                    NOT NULL COMMENT '是否删除'
) comment '游戏表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS game_mark;
CREATE TABLE game_mark
(
    id          bigint AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id     varchar(255)                       NOT NULL COMMENT '用户id',
    user_name   varchar(255)                       NOT NULL COMMENT '用户昵称',
    user_avatar varchar(255)                       NOT NULL COMMENT '用户头像',
    game_id     bigint                             NOT NULL COMMENT '游戏id',
    image       varchar(255)                       NOT NULL COMMENT '游戏图片',
    score       int(1)                             NOT NULL COMMENT '评分',
    `comment`   varchar(128)                       NOT NULL COMMENT '评价',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete   tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '游戏评分表' collate = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS comment_message;
CREATE TABLE comment_message
(
    id              bigint auto_increment comment 'id' PRIMARY KEY,
    user_id         varchar(255)                       NOT NULL COMMENT '被评论用户id',
    post_comment_id bigint                             NOT NULL COMMENT '帖子评论id',
    status          tinyint  DEFAULT 0                 NOT NULL COMMENT '0->未读，1->已读',
    create_time     datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time     datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间' ON UPDATE CURRENT_TIMESTAMP,
    is_delete       tinyint  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) comment '评论消息表' collate = utf8mb4_unicode_ci;



