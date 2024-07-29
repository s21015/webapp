-- テーブルが存在したら削除する
DROP TABLE IF EXISTS todos;

-- テーブルの作成
CREATE TABLE todos (
   -- id(することID)：主キー
   id serial PRIMARY KEY,
   -- todo(すること)；NULL不許可
   todo varchar (255) NOT null,
   -- detail(説明)
   detail text,
   -- created_at(作成日)
   created_at timestamp without time zone,
   -- updated_at(更新日)
   updated_at timestamp without time zone,
   -- usrename(ユーザー名)
   username varchar(50) NOT null
);

DROP TABLE IF EXISTS accounts;

create table accounts (
  username character varying(50) not null
  , password character varying(500) not null
  , role character varying(50) not null
  , primary key (username)
);

DROP TABLE IF EXISTS threads;

create table threads (
  id serial not null
  , title_id integer
  , thread character varying(500)
  , username character varying(500)
  , created_at timestamp(6) without time zone
  , primary key (id)
);

DROP TABLE IF EXISTS titles;

create table titles (
  id serial not null
  , title character varying(500)
  , created_at timestamp(6) without time zone
  , updated_at timestamp(6) without time zone
  , primary key (id)
);

