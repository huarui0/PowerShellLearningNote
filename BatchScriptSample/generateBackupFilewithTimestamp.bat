# Generate a Backup File with Timestamp using a Batch Script
# https://datatofish.com/backup-file-timestamp/

@echo off

for /f "delims=" %%a in ('wmic OS Get localdatetime ^| find "."') do set DateTime=%%a

set Yr=%DateTime:~0,4%
set Mon=%DateTime:~4,2%
set Day=%DateTime:~6,2%
set Hr=%DateTime:~8,2%
set Min=%DateTime:~10,2%
set Sec=%DateTime:~12,2%

set BackupName= File Name__%Yr%-%Mon%-%Day%_(%Hr%-%Min%-%Sec%)

copy "Path where your file is stored\File Name.File Type" "Path where your backup file will be stored\%BackupName%.File Type"