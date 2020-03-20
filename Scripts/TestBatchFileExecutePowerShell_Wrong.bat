set LOG_FILE = "GDGAGnklasj;oks;fk;dkf lkl;"
set oName = Name
set oStart = "%YYYY%%MM%%DD% %TIME%" 
set oStatus = 0
set oEnd = "%YYYY%%MM%%DD% %TIME%" 
set oDateRan = %YYYY%%MM%%DD%
set oLog =for /f "delims=" %%i in (%LOG_FILE%) do set content=%content% %%i
echo Updating Database >> %LOG_FILE% 2>&1
cmd /S powershell.exe -ExecutionPolicy Bypass -File ".\Reporting\updateTool.ps1" "%oName%" "%DateRan%" "%oStart%" "%oEnd%" "%oStatus "%oLog%