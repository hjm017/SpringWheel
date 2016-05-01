create table message (
	id bigint ,
	receiver_id bigint null,
	message varchar(256),
	receive_date timestamp,
	primary key (id)
);