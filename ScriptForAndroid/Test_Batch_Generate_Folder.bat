@(
   SETLOCAL ENABLEDELAYEDEXPANSION
   ECHO OFF

   SET "_PSScript=E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Test_Generate_FolderTool.ps1"
   REM SET "_DebugPreference=Continue"
   SET "_DebugPreference=SilentlyContinue"

   SET "_LOG_FILE=E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\default.log"
   SET "_oName=myName:wanglai"
   SET "_oStart=%YYYY%%MM%%DD% %TIME: =0%"
   SET /a "_Status=0"
   SET "_oEnd=%YYYY%%MM%%DD% %TIME: =0%" 
   SET "_oDateRan=%YYYY%%MM%%DD% %TIME: =0%"
)

SET "_PSCMD=Powershell "%_PSScript%" -DebugPreference "%_DebugPreference%" -LOG_FILE "%_LOG_FILE%" -oName "%_oName%" -oStart "%_oStart%" -Status %_Status% -oEnd "%_oEnd%" -oDateRan "%_oDateRan%" "


%_PSCMD% 2>&1 >> "_LOG_FILE"