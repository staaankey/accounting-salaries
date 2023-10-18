CREATE TABLE "users" (
    "user_id" serial NOT NULL,
    "login" varchar NOT NULL UNIQUE,
    "password" varchar NOT NULL,
    CONSTRAINT "users_pk" PRIMARY KEY ("user_id")
);

CREATE TABLE "timesheets" (
     "timesheet_id" serial NOT NULL,
     "schedule" date[] NOT NULL,
     "hours" integer NOT NULL,
     "user_id" integer NOT NULL,
     CONSTRAINT "timesheets_pk" PRIMARY KEY ("timesheet_id")
);

create table "departments" (
    "department_id" serial NOT NULL,
    parent_id integer not null,
    "name" varchar not null,
    CONSTRAINT  "departments_pk" PRIMARY KEY ("department_id")
);

CREATE TABLE "employers" (
    "employee_id" serial NOT NULL,
    "full_name" varchar NOT NULL,
    "photo_url" varchar NOT NULL,
    "phone" varchar NOT NULL,
    "address" varchar NOT NULL,
    "passport_id" integer NOT NULL,
    "workplace_id" integer NOT NULL,
    "titles_id" integer NOT NULL,
    "education_id" integer NOT NULL,
    "department_id" integer NOT NULL,
    CONSTRAINT "employers_pk" PRIMARY KEY ("employee_id")
);



CREATE TABLE "passports" (
    "passport_id" serial NOT NULL,
    "series" varchar NOT NULL,
    "date_of_birth" DATE NOT NULL,
    "place_of_birth" varchar NOT NULL,
    "date_of_issue" DATE NOT NULL,
    "date_of_expiring" DATE NOT NULL,
    "place_of_issue" varchar NOT NULL,
    CONSTRAINT "passports_pk" PRIMARY KEY ("passport_id")
);



CREATE TABLE "workplaces" (
     "workplace_id" serial NOT NULL,
     "position" varchar NOT NULL,
     "day_of_start" DATE NOT NULL,
     CONSTRAINT "workplaces_pk" PRIMARY KEY ("workplace_id")
);



CREATE TABLE "educations" (
     "education_id" serial NOT NULL,
     "degree" varchar NOT NULL,
     "diploma_number" integer NOT NULL,
     "diploma_series" varchar NOT NULL,
     "university_name" varchar NOT NULL,
     CONSTRAINT "educations_pk" PRIMARY KEY ("education_id")
);



CREATE TABLE "titles" (
     "title_id" serial NOT NULL,
     "title_name" varchar NOT NULL,
     "speciality" integer NOT NULL,
     "serial_number" varchar NOT NULL,
     "date_of_issue" DATE NOT NULL,
     "name_of_place" integer NOT NULL,
     CONSTRAINT "titles_pk" PRIMARY KEY ("title_id")
);



ALTER TABLE "employers" ADD CONSTRAINT "employers_fk0" FOREIGN KEY ("passport_id") REFERENCES "passports"("passport_id");
ALTER TABLE "employers" ADD CONSTRAINT "employers_fk1" FOREIGN KEY ("workplace_id") REFERENCES "workplaces"("workplace_id");
ALTER TABLE "employers" ADD CONSTRAINT "employers_fk2" FOREIGN KEY ("titles_id") REFERENCES "titles"("title_id");
ALTER TABLE "employers" ADD CONSTRAINT "employers_fk3" FOREIGN KEY ("education_id") REFERENCES "educations"("education_id");
ALTER TABLE "employers" ADD CONSTRAINT "employers_fk4" FOREIGN KEY ("department_id") REFERENCES "departments"("department_id");
ALTER TABLE "timesheets" ADD CONSTRAINT "timesheets_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("user_id");

