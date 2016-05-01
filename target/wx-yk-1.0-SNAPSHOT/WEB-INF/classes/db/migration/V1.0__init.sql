create table book (
   id bigint ,
   douban_id varchar(64) not null,
   title varchar(128) not null,
   url varchar(255),
   description varchar(255),
   owner_id bigint not null,
   onboard_date timestamp,
   status varchar(32) not null,
   borrower_id bigint null,
   borrow_date timestamp,
   primary key (id)
);

create table account (
   id bigint ,
   name varchar(64) not null,
   email varchar(128),
   hash_password varchar(255),
   primary key (id)
);


insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(1,'25984046', 'Big Data日知录', 'http://book.douban.com/subject/25984046/','', 1,'idle','2015-01-01');
insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(2,'25900156', 'Redis设计与实现', 'http://book.douban.com/subject/25900156/','', 1,'idle','2015-01-02');
insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(3,'25741352', 'DSL实战', 'http://book.douban.com/subject/25741352/','', 2,'idle','2015-01-03');

insert into account (id,email,name,hash_password) values(1,'calvin.xiao@springside.io','Calvin','+2MunThvGcEfdYIFlT4NQQHt6z4=');
insert into account (id,email,name,hash_password) values(2,'david.wang@springside.io','David','+2MunThvGcEfdYIFlT4NQQHt6z4=');
/*==============================================================*/
/* Table: c_device_plan                                         */
/*==============================================================*/
create table c_device_plan
(
   id                   bigint(20) not null comment 'id',
   begin_time           datetime not null comment '开始时间',
   end_time             datetime not null comment '结束时间',
   c_model_id           bigint(20) not null comment '模板id',
   model_name           varchar(200) not null comment '模板名称',
   model_type           tinyint(3) not null comment '模板类型（bs_mblx）1：竖排版  2：横排版',
   w_site_id            bigint(20) not null comment '站点id',
   w_device_id          bigint(20) not null comment '设备id',
   token                int(10) not null comment '设备标识',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table c_device_plan comment '设备计划表';

/*==============================================================*/
/* Index: Index_model_id                                        */
/*==============================================================*/
create unique index Index_model_id on c_device_plan
(
   c_model_id
);

/*==============================================================*/
/* Index: Index_site_id                                         */
/*==============================================================*/
create unique index Index_site_id on c_device_plan
(
   w_site_id
);

/*==============================================================*/
/* Index: Index_device_id                                       */
/*==============================================================*/
create unique index Index_device_id on c_device_plan
(
   w_device_id
);

/*==============================================================*/
/* Table: c_model                                               */
/*==============================================================*/
create table c_model
(
   id                   bigint(20) not null comment 'id',
   model_name           varchar(200) not null comment '模板名称',
   model_type           tinyint(3) not null comment '模板类型（bs_mblx）1：竖排版  2：横排版',
   layout               tinyint(3) not null comment '版式（bs_mbbs）1：图片 2：视频 3：混合版式',
   is_use               tinyint(3) not null comment '是否使用（0：否 1：是）',
   is_default           tinyint(3) not null comment '是否默认（0：否 1：是）',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table c_model comment '模板表';

/*==============================================================*/
/* Table: c_model_item                                          */
/*==============================================================*/
create table c_model_item
(
   id                   bigint(20) not null comment 'id',
   c_model_id           bigint(20) not null comment '模板id',
   resource_type        tinyint(3) not null comment '资源类型（bs_zylx）1：图片  2：视屏 3：商品',
   r_image_id           tinyint(3) comment '图片id',
   image_name           varchar(200) comment '图片名称',
   image_uri            varchar(200) comment '图片路径',
   image_size_x         tinyint(5) comment '图片尺寸X',
   image_size_y         tinyint(5) comment '图片尺寸Y',
   r_video_id           bigint(20) comment '视屏id',
   video_name           varchar(200) comment '视屏名称',
   video_uri            varchar(500) comment '视屏链接',
   r_product_id         bigint(20) comment '商品id',
   product_brand        varchar(200) comment '商品品牌',
   product_name         varchar(200) comment '商品名称',
   product_cover_uri    varchar(200) comment '商品封面路径',
   sort                 tinyint(3) not null comment '排序',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table c_model_item comment '模板碎片表';

/*==============================================================*/
/* Index: Index_model_id                                        */
/*==============================================================*/
create unique index Index_model_id on c_model_item
(
   c_model_id
);

/*==============================================================*/
/* Table: f_device                                              */
/*==============================================================*/
create table f_device
(
   id                   bigint(20) not null comment 'id',
   remark               varchar(200) not null comment '备注',
   w_site_id            bigint(20) not null comment '站点id',
   site_name            varchar(100) not null comment '站点名称',
   area                 varchar(200) not null comment '地区',
   address              varchar(200) not null comment '详细地址',
   longitude            decimal(10,7) not null comment '经度',
   latitude             decimal(10,7) not null comment '纬度',
   director             varchar(100) not null comment '负责人',
   telephone            varchar(20) not null comment '联系电话',
   token                tinyint(3) not null comment '设备标识',
   status               tinyint(3) not null comment '状态（bs_sbzt）1：离线 2：在线',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table f_device comment '设备表';

/*==============================================================*/
/* Table: r_album                                               */
/*==============================================================*/
create table r_album
(
   id                   bigint(20) not null comment 'id',
   album_name           varchar(200) not null comment '相册名称',
   is_default           tinyint(3) not null comment '是否默认（0：否 1：是）',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table r_album comment '相册表';

/*==============================================================*/
/* Table: r_image                                               */
/*==============================================================*/
create table r_image
(
   id                   bigint(20) not null comment 'id',
   image_name           varchar(200) not null comment '图片名称',
   image_type           tinyint(3) not null comment '图片格式（bs_tpgs）1：JPG 2：PNG 3：GIF',
   image_big_uri        varchar(200) not null comment '大图路径',
   image_small_uri      varchar(200) not null comment '缩略图路径',
   image_size_x         tinyint(6) not null comment '图片尺寸X',
   image_size_y         tinyint(6) not null comment '图片尺寸Y',
   r_album_id           bigint(20) not null comment '相册id',
   album_name           varchar(200) not null comment '相册名称',
   is_use               tinyint(3) not null comment '是否使用（0：未使用 1：已使用）',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table r_image comment '图片表';

/*==============================================================*/
/* Index: Index_album_id                                        */
/*==============================================================*/
create unique index Index_album_id on r_image
(
   r_album_id
);

/*==============================================================*/
/* Table: r_product                                             */
/*==============================================================*/
create table r_product
(
   id                   bigint(20) not null comment 'id',
   brand                varchar(200) not null comment '商品品牌',
   product_name         varchar(200) not null comment '商品名称',
   product_price        bigint(20) not null comment '商品价格',
   product_link         varchar(500) not null comment '商品链接',
   img_cover            varchar(200) not null comment '商品列表图',
   img_main             varchar(200) not null comment '商品主图',
   img_side             varchar(200) not null comment '商品侧面图',
   img_1                varchar(200) not null comment '商品细节图1',
   img_2                varchar(200) not null comment '商品细节图2',
   img_detail           varchar(200) not null comment '商品详情',
   source               tinyint(3) not null comment '商品来源（bs_sply）1：咔咪 2：京东 3：天猫 4：亚马逊',
   platform_no          varchar(100) not null comment '平台货号',
   is_use               tinyint(3) not null comment '是否使用（0：未使用 1：已使用）',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table r_product comment '商品表';

/*==============================================================*/
/* Table: r_video                                               */
/*==============================================================*/
create table r_video
(
   id                   bigint(20) not null comment 'id',
   video_name           varchar(200) not null comment '视屏名称',
   video_uri            varchar(200) not null comment '视屏路径',
   video_type           tinyint(3) not null comment '视屏格式（bs_spgs）1：avi 2：wmv 3：rmvb',
   video_ratio          varchar(100) not null comment '视屏分辨率',
   img_small_uri        varchar(200) not null comment '缩略图',
   r_album_id           bigint(20) not null comment '相册id',
   album_name           varchar(200) not null comment '相册名称',
   is_use               tinyint(3) not null comment '是否使用（0：未使用 1：已使用）',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table r_video comment '视频表';

/*==============================================================*/
/* Index: Index_album_id                                        */
/*==============================================================*/
create unique index Index_album_id on r_video
(
   r_album_id
);

/*==============================================================*/
/* Table: w_site                                                */
/*==============================================================*/
create table w_site
(
   id                   bigint(20) not null comment 'id',
   site_name            varchar(100) not null comment '站点名称',
   site_identify        varchar(100) not null comment '站点标识',
   director             varchar(100) not null comment '负责人',
   telephone            varchar(20) not null comment '站点电话',
   area                 varchar(200) not null comment '地区',
   address              varchar(200) not null comment '详细地址',
   longitude            decimal(10,7) not null comment '经度',
   latitude             decimal(10,7) not null comment '纬度',
   description          varchar(500) not null comment '站点描述',
   is_delete            tinyint(3) not null comment '是否删除（0：未删除 1：已删除）',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table w_site comment '站点表';

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/1 19:06:38                            */
/*==============================================================*/


drop table if exists b_dict;

drop table if exists b_dict_type;

drop table if exists e_bill;

drop table if exists e_cart;

drop table if exists e_order;

drop table if exists e_refund;

drop table if exists g_ad;

drop table if exists g_ad_draft;

drop table if exists g_matter;

drop table if exists g_wx_send;

drop table if exists m_bbs;

drop table if exists p_account;

drop table if exists p_user;

drop table if exists p_user_goods;

drop table if exists p_user_gzh;

drop table if exists p_user_provider;

drop table if exists s_goods;

drop table if exists w_category;

drop table if exists w_gzh;

drop table if exists w_gzh_message;

/*==============================================================*/
/* Table: b_dict                                                */
/*==============================================================*/
create table b_dict
(
   id                   bigint(20) not null comment 'id',
   dict_name            varchar(50) not null comment '字典名称',
   dict_value           varchar(50) not null comment '字典值',
   b_dict_type_id       bigint(20) not null comment '字典类型id',
   dict_type_name       varchar(50) not null comment '字典类型名称',
   dict_type_value      varchar(50) not null comment '字典类型值',
   sort                 int(10) not null comment '排序',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table b_dict comment '字典表';

/*==============================================================*/
/* Table: b_dict_type                                           */
/*==============================================================*/
create table b_dict_type
(
   id                   bigint(20) not null comment 'id',
   dict_type_name       varchar(50) not null comment '字典类型名称',
   dict_type_value      varchar(50) not null comment '字典类型值',
   sort                 int(10) not null comment '排序',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table b_dict_type comment '字典类型表';

/*==============================================================*/
/* Table: e_bill                                                */
/*==============================================================*/
create table e_bill
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) comment '用户id',
   bill_no              varchar(20) not null comment '结算编号',
   bill_price           bigint(20) not null comment '结算金额',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table e_bill comment '结算表';

/*==============================================================*/
/* Table: e_cart                                                */
/*==============================================================*/
create table e_cart
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) not null comment '用户id',
   g_ad_id              bigint(20) comment '广告id',
   ad_type              tinyint(3) not null comment '广告类型',
   ad_name              varchar(50) not null comment '广告名称',
   ad_place             varchar(50) not null comment '广告位置',
   ad_price             bigint(20) not null comment '广告价格',
   w_gzh_id             bigint(20) not null comment '公众号id',
   gzh_no               varchar(20) not null comment '公众号',
   gzh_name             varchar(50) not null comment '公众号名称',
   fit                  tinyint(5) not null comment '配合度',
   fans                 bigint(20) not null comment '粉丝数',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table e_cart comment '购物车';

/*==============================================================*/
/* Table: e_order                                               */
/*==============================================================*/
create table e_order
(
   id                   bigint(20) not null comment 'id',
   order_no             varchar(20) not null comment '订单编号',
   g_ad_id              bigint(20) not null comment '广告id',
   ad_name              varchar(50) not null comment '广告名称',
   ad_place             varchar(20) comment '广告位置',
   ad_link              varchar(500) comment '广告链接',
   ad_confirm_link      varchar(500) comment '广告确认链接',
   p_user_id            bigint(20) comment '广告主id',
   user_name            varchar(50) comment '广告主姓名',
   p_provider_id        bigint(20) comment '媒体人id',
   provider_name        varchar(50) comment '媒体人名称',
   w_gzh_id             bigint(20) not null comment '公众号id',
   gzh_name             varchar(50) not null comment '公众号名称',
   task_time            datetime not null comment '任务时间',
   exec_time            datetime not null comment '执行时间',
   order_price          bigint(20) not null comment '订单价格',
   ask_state            tinyint(3) comment '申述情况',
   has_bill             tinyint(3) comment '是否生成结算单 0：未生成  1：已生成',
   order_state          tinyint(3) not null comment '订单状态（order_state）1：等待接单 2：等待上传 3：流单 4：等待结算 5：验收申诉 6：完成订单',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table e_order comment '订单表';

/*==============================================================*/
/* Table: e_refund                                              */
/*==============================================================*/
create table e_refund
(
   id                   bigint(20) not null comment 'id',
   refund_no            varchar(20) comment '退款编号',
   e_order_id           bigint(20) not null comment '订单id',
   order_no             varchar(20) not null comment '订单编号',
   order_price          bigint(20) not null comment '订单金额',
   refund_pay_price     bigint(20) comment '退款金额',
   refund_state         tinyint(3) not null comment '退款状态(refund_state) 1：待审核 2：退款成功 3：退款失败',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table e_refund comment '退款表';

/*==============================================================*/
/* Table: g_ad                                                  */
/*==============================================================*/
create table g_ad
(
   id                   bigint(20) not null comment 'id',
   ad_type              tinyint(3) not null comment '广告类型',
   ad_name              varchar(50) not null comment '广告名称',
   ad_place             varchar(50) not null comment '广告位置',
   w_gzh_id             bigint(20) not null comment '公众号id',
   gzh_name             varchar(50) not null comment '公众号名称',
   price                bigint(20) not null comment '价格',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table g_ad comment '广告表';

/*==============================================================*/
/* Table: g_ad_draft                                            */
/*==============================================================*/
create table g_ad_draft
(
   id                   bigint(20) not null comment 'id',
   ad_type              tinyint(3) not null comment '广告类型',
   ad_name              varchar(50) not null comment '广告名称',
   ad_link              varchar(200) not null comment '广告链接',
   task_begin_time      datetime not null comment '任务开始时间',
   task_end_time        datetime comment '任务结束时间',
   message              varchar(500) comment '留言',
   is_issue             tinyint(3) comment '是否发布',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table g_ad_draft comment '广告草稿表';

/*==============================================================*/
/* Table: g_matter                                              */
/*==============================================================*/
create table g_matter
(
   id                   bigint(20) not null comment 'id',
   matter_type          tinyint(3) not null comment '素材类型（matter_type）1：云端素材 2：公众号素材 3：素材模板',
   matter_name          varchar(50) not null comment '素材名称',
   matter_link          varchar(200) not null comment '素材链接',
   title                varchar(50) not null comment '标题',
   author               datetime not null comment '作者',
   cover                varchar(200) not null comment '封面',
   summary              varchar(500) not null comment '摘要',
   show_cover           tinyint(3) not null comment '是否显示封面（0：显示 1：不显示）',
   origin_link          varchar(200) comment '原文链接',
   w_gzh_id             bigint(20) comment '公众号id',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table g_matter comment '素材表';

/*==============================================================*/
/* Table: g_wx_send                                             */
/*==============================================================*/
create table g_wx_send
(
   id                   bigint(20) not null comment 'id',
   g_matter_id          bigint(20) not null comment '素材id',
   matter_link          varchar(200) not null comment '素材链接',
   phone_number         varchar(20) not null comment '手机号',
   cron_time            datetime comment '定时发送时间',
   cron_state           tinyint(3) comment '定时发送状态（0：未发送 1：已发送）',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table g_wx_send comment '微信群发表';

/*==============================================================*/
/* Table: m_bbs                                                 */
/*==============================================================*/
create table m_bbs
(
   id                   bigint(20) not null comment 'id',
   title                varchar(100) not null comment '标题',
   content              varchar(500) not null comment '内容',
   attachment           varchar(200) comment '附件',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table m_bbs comment '论坛帖子表';

/*==============================================================*/
/* Table: p_account                                             */
/*==============================================================*/
create table p_account
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) comment '用户id',
   user_name            varchar(50) not null comment '用户名',
   password             varchar(50) not null comment '密码',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table p_account comment '运营账号表';

/*==============================================================*/
/* Table: p_user                                                */
/*==============================================================*/
create table p_user
(
   id                   bigint(20) not null comment 'id',
   user_name            varchar(50) not null comment '用户名',
   user_type            tinyint(3) comment '用户类型（user_type）1：广告主  2：媒体人',
   real_name            varchar(50) not null comment '真实姓名',
   password             varchar(50) not null comment '密码',
   phone_number         varchar(20) not null comment '手机号码',
   company_name         varchar(100) comment '公司名',
   icon                 varchar(200) comment '头像',
   email                varchar(50) comment '邮箱',
   balance              char(10) not null comment '余额',
   score                bigint(20) not null comment '积分',
   fit                  tinyint(5) not null comment '配合度',
   qq                   varchar(20) comment 'QQ',
   weixin               varchar(20) comment '微信',
   alipay               varchar(30) comment '支付宝',
   bank_type            tinyint(3) comment '银行类型',
   bank_account         varchar(50) comment '银行账号',
   receive_address      varchar(200) comment '收货地址',
   w_gzh_id             bigint(20) comment '公众号id',
   gzh_no               varchar(30) comment '公众号',
   gzh_name             varchar(50) comment '公众号名称',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table p_user comment '用户表';

/*==============================================================*/
/* Table: p_user_goods                                          */
/*==============================================================*/
create table p_user_goods
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) not null comment '用户id',
   s_goods_id           bigint(20) not null comment '商品id',
   goods_name           varchar(50) not null comment '商品名称',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table p_user_goods comment '用户已兑换商品表';

/*==============================================================*/
/* Table: p_user_gzh                                            */
/*==============================================================*/
create table p_user_gzh
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) not null comment '用户id',
   user_name            varchar(50) not null comment '用户名',
   real_name            varchar(50) not null comment '真实姓名',
   phone_number         varchar(20) not null comment '手机号码',
   w_gzh_id             bigint(20) not null comment '公众号id',
   gzh_name             varchar(50) not null comment '公众号名称',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table p_user_gzh comment '用户公众号表';

/*==============================================================*/
/* Table: p_user_provider                                       */
/*==============================================================*/
create table p_user_provider
(
   id                   bigint(20) not null comment 'id',
   p_user_id            bigint(20) not null comment '用户id',
   user_name            varchar(50) not null comment '用户名',
   real_name            varchar(50) not null comment '真实姓名',
   phone_number         varchar(20) not null comment '手机号码',
   p_provider_id        bigint(20) not null comment '服务商id',
   provider_name        varchar(50) not null comment '服务商名称',
   provider_icon        varchar(200) comment '头像',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table p_user_provider comment '用户提供商表';

/*==============================================================*/
/* Table: s_goods                                               */
/*==============================================================*/
create table s_goods
(
   id                   bigint(20) not null comment 'id',
   goods_type           tinyint(3) not null comment '广告类型',
   goods_name           varchar(50) not null comment '广告名称',
   icon                 varchar(200) comment '商品图标',
   goods_link           varchar(200) not null comment '广告链接',
   score                bigint(20) not null comment '兑换积分',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table s_goods comment '积分商品表';

/*==============================================================*/
/* Table: w_category                                            */
/*==============================================================*/
create table w_category
(
   id                   bigint(20) not null comment 'id',
   category_name        varchar(50) not null comment '类别名称',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table w_category comment '类别表';

/*==============================================================*/
/* Table: w_gzh                                                 */
/*==============================================================*/
create table w_gzh
(
   id                   bigint(20) not null comment 'id',
   gzh_no               varchar(30) not null comment '公众号',
   gzh_name             varchar(50) not null comment '公众号名称',
   gzh_type             varchar(50) not null comment '公众号类型',
   icon                 varchar(100) not null comment '头像',
   qr_code              varchar(500) not null comment '二维码',
   introduction         varchar(1000) not null comment '功能介绍',
   author_state         tinyint(3) not null comment '认证状态',
   sex_ratio            varchar(20) comment '性别比例',
   price                bigint(20) not null comment '价格',
   fans                 bigint(20) not null comment '粉丝',
   token                varchar(200) not null comment 'token',
   w_category_id        bigint(20) not null comment '公众号类别id',
   category_name        varchar(100) not null comment '公众号类别',
   body                 varchar(200) comment '主体信息',
   address              varchar(200) comment '地址',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table w_gzh comment '公众号表';

/*==============================================================*/
/* Table: w_gzh_message                                         */
/*==============================================================*/
create table w_gzh_message
(
   id                   bigint(20) not null comment 'id',
   w_gzh_id             bigint(20) not null comment '公众号id',
   gzh_name             varchar(50) not null comment '公众号名称',
   content              varchar(1000) not null comment '留言',
   p_user_id            bigint(20) not null comment '留言人id',
   user_name            varchar(20) not null comment '留言人姓名',
   is_delete            tinyint(3) not null comment '是否删除',
   created_at           datetime not null comment '创建时间',
   created_by           varchar(20) not null comment '创建人',
   changed_at           datetime comment '更新时间',
   changed_by           varchar(20) comment '更新人',
   primary key (id)
);

alter table w_gzh_message comment '运营账号表';

