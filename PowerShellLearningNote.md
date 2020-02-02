# Windows
## PowerShell & VBScript
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
      + 参考
         - [How To Replace Text in a File with PowerShell](https://mcpmag.com/articles/2018/08/08/replace-text-with-powershell.aspx)<br>
         - [Using PowerShell to replace text in a file](https://adamtheautomator.com/powershell-replace-text-in-file/)<br>
         - [Replace string in PowerShell Script using Get-Content and Set-Content](https://social.technet.microsoft.com/Forums/windowsserver/en-US/db2b9342-70b0-4577-b87c-d666f2b40c35/replace-string-in-powershell-script-using-getcontent-and-setcontent?forum=winserverpowershell)<br>
         - YouTube
            * [How To Replace Text In A File With PowerShell - YouTube](https://www.youtube.com/watch?v=VbtTCLFjr7w)<br>
      + 方法
         1. 直接替换文件内容
            - 步骤
            
         2. 建立临时文件，修改后，替换原来文件。注：替换前，更改文件名称，或复制一个。
            - 步骤
### 知识点总结
   * 如何传递参数（parameters）
      + 参考
         - []()<br>
      + 方法
         1. 通过变量
         ```powershell
             $hostfile = "C:\Users\username\Desktop\csvfile_test.csv"
             $outFile = ".\testerFile.xlsx"
             $oldString = "foo"
             $newString = "bar"
             .\organizer.ps1 -csvFile $hostfile -outputPath $outFile
         ```
      + 注意：**单引号与双引号的区别**
   * 如何流程控制(Flow Control Statements)
   * 脚本执行模式
      1. Use single quotes or double quotes - 在PowerShell App中或Cmd终端中适用
         + 命令格式：
         ```shell
             .\TestQuotes.ps1 -Config "A B C" 或者  .\TestQuotes.ps1 -Config 'A B C'
         ```
         + 结果如下：
         ```shell
             PS E:\Notes\4_ComputeCourse\PowerShell\Script>  .\TestQuotes.ps1 -Config 'A B C' -Country '中国’
             Config = A B C
             Country = 中国
            ```
      2. Use only the PowerShell without -file argument
         + 命令格式
         ```shell
             PowerShell .\TestQuotes.ps1 -Config "A B C" - 用PowerShell 参数的方式不同：所有参数均包含在""中。 
         ```
         ```shell
             E:\Notes\4_ComputeCourse\PowerShell\Script>powershell .\test1.ps1 -x 'hello world' -y 'my friend'
             hello world
             my friend
         ```
      3. Use the PowerShell with -file argument
         + 命令格式
         ```shell
             PowerShell .\TestQuotes.ps1 -Config "A B C" - 用PowerShell 参数的方式不同：所有参数均包含在""中。 
         ```
         + 结果如下：
         ```shell
             PS E:\Notes\4_ComputeCourse\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"
             Config = A
             Country = B
             PS E:\Notes\4_ComputeCourse\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"  -Country '中国'
             Config = A
             Country = 中国
         ```
      3. 通过批处理文件执行PowerShell脚本的方法：（因时间的关系，脚本执行结果有错，后续更正，但，方法就是这样的）
         - 参考
            * [How to pass batch file variables to PowerShell script? [duplicate]](https://stackoverflow.com/questions/56961935/how-to-pass-batch-file-variables-to-powershell-script)<br>
         - 测试文件位置
            * powershell文件：E:\Notes\4_ComputeCourse\PowerShell\Script\updateTool.ps1
            ```shell
               ## Script: UpdateTool.ps1
               # 
               param(
                   [String]$LOG_FILE = 'E:\Notes\4_ComputeCourse\PowerShell\Script\TestFolder\default.log',
                   [String]$oName = 'default name',
                   [String]$oStart = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
                   [Int]$oStatus = 0,
                   [String]$oEnd = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
                   [String]$oDateRan = $(Get-date -F "yyyyMMdd"),
                   $DebugPreference = "SilentlyContinue"
               )

               "This is $oName"
            ```
            * 批处理文件（bat)：E:\Notes\4_ComputeCourse\PowerShell\Script\Test_BatchFile_updateTool.bat
            ```vbscript
               @(
                  SETLOCAL ENABLEDELAYEDEXPANSION
                  ECHO OFF

                  SET "_PSScript=E:\Notes\4_ComputeCourse\PowerShell\Script\updateTool.ps1"
                  REM SET "_DebugPreference=Continue"
                  SET "_DebugPreference=SilentlyContinue"

                  SET "_LOG_FILE=GDGAGnklasj;oks;fk;dkf lkl;"
                  SET "_oName=Name"
                  SET "_oStart=%YYYY%%MM%%DD% %TIME: =0%"
                  SET /a "_Status=0"
                  SET "_oEnd=%YYYY%%MM%%DD% %TIME: =0%" 
                  SET "_oDateRan=%YYYY%%MM%%DD%"
               )

               SET "_PSCMD=Powershell "%_PSScript%" -DebugPreference "%_DebugPreference%" -LOG_FILE "%_LOG_FILE%" -oName "%_oName%" -oStart "%_oStart%" -Status %_Status% -oEnd "%_oEnd%" -oDateRan "%_oDateRan%" "

               %_PSCMD% 2>&1 >> "_LOG_FILE"
            ```
### 高级操作
   * 参考
      + [Managing Microsoft PowerApps and Flow Like a Pro – Part 1](https://www.syskit.com/blog/managing-microsoft-powerapps-and-flow-like-a-pro-pt1/)<br>
      + [How to pass parameters to the script while calling them inside the provisioner](https://stackoverflow.com/questions/54587146/how-to-pass-parameters-to-the-script-while-calling-them-inside-the-provisioner) - provisioner 不知是啥，后续学习<br>
# Linux及MacOS
## Bash Shell script
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
