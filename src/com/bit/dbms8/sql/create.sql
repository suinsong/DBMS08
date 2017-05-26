



create table buyer(
	id int not null auto_increment,
	bucode varchar(10) not null,
	buname varchar(15) not null,
	butel varchar(15) not null,
	buaddr varchar(50) default "우리동네",
	bupoint int default 0,
	primary key(id)
	
);

create table names(
	id int not null auto_increment,
	egname varchar(30),
	hgname varchar(30),
	means varchar(150),
	
	primary key(id)

);





