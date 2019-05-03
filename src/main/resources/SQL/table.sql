create table `user`(
    id bigint primary key ,
    userName varchar(50),
    authorityName varchar(10),
    password varchar(22)
)engine = InnoDB DEFAULT CHARSET=utf8;