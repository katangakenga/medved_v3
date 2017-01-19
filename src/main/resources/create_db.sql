

create user medved
identified by medved
default tablespace system
quota 10m on system
temporary tablespace temp
quota 5m on system;
grant connect to medved;
grant all privileges to medved;
