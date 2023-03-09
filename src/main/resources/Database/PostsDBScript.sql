drop database if exists BlogPost;

create database BlogPost;

use BlogPost;

-- Posts
create table posts(
	id int primary key auto_increment,
    content text not null,
    created_date datetime not null,
    title varchar(255),
    added_by varchar(100)
);
