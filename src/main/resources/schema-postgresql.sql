create table if not exists "user" (
	id bigserial primary key,
	username text,
	email text,
	phone text,
	first_name text,
	last_name text,
	birth_date date,
	profession text,
	current_location text
);

create table if not exists post (
	id bigserial primary key,
	title text,
	content text,
	created_at timestamptz,
	last_updated_at timestamptz,
	user_id bigint references "user"(id)
);

create table if not exists comment (
	id bigserial primary key,
	content text,
	created_at timestamptz,
	last_updated_at timestamptz,
	post_id bigint references post(id),
	user_id bigint references "user"(id)
);
