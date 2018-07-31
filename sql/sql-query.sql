create table users (
	username varchar(50) not null,
    password varchar(68) not null,
    enabled tinyint(1) not null,
    primary key (username)
    ) engine = InnoDB default charset = latin1;
    
insert into users values ('ram', '{noop}123',1), ('brijesh', '{noop}123',1), ('ashish', '{noop}123',1);

create table authorities (
	username varchar(50) not null,
    authority varchar(50) not null,
    
    unique key authorities_idx_1 (username, authority),
    constraint authorities_ibfk_1
    foreign key (username)
    references users (username)
    
    ) engine = InnoDB default charset = latin1;
    
insert into authorities values ('ram', 'ROLE_EMPLOYEE'), ('brijesh', 'ROLE_EMPLOYEE'), ('brijesh', 'ROLE_MANAGER'), 
('ashish', 'ROLE_EMPLOYEE'), ('ashish', 'ROLE_ADMIN');