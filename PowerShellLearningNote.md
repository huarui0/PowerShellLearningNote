# Windows
## PowerShell & VBScript
   * 参考
      + [Starting Windows PowerShell](https://docs.microsoft.com/en-us/powershell/scripting/getting-started/starting-windows-powershell?view=powershell-7.x)<br>
      + [Powershell 编写和运行脚本](https://www.pstips.net/powershell-create-and-start-scripts.html) - 参考其中的几个方法<br>
      + [从零开始——PowerShell应用入门（全例子入门讲解）](https://www.cnblogs.com/lavender000/p/6935589.html)<br>
   * 重要提示
      + 查询命令用法
      ```shell
           Get-Command -Verb Get
           Get-Command -Noun Variable
           Remove-Variable -Name * -Force -ErrorAction SilentlyContinue
      ```
      + 脚本执行权限 - 执行脚本前，需要设置为，执行完应该
         - 命令格式
            * 查询当前执行策略
            ```shell
                Get-ExecutionPolicy
            ```
            * 执行脚本前，需要设置为：Unrestricted
            ```shell
                Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy Unrestricted
            ```
            * 执行完脚本，设置为：Restricted
            ```shell
                Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy Restricted
            ```
            * 其他权限，需要研究，比如，远程权限等等。
            ```shell
                Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
            ```
      + 如何执行命令
         - 如何在外部调用PowerShell脚本
         ```shell
         
         ```
         - 如何获知最后执行命令的状态：$lastExitCode：数字型变量，返回最后脚本或应用程序执行返回的退出码或出错级别：，$?：布尔型变量，返回最后执行命令的成功还是失败：
         ```shell
             $LastExitCode
             $?
         ```
         - 如何操作使用管理控制台历史命令
         >在PowerShell窗口中，按上下箭头键可以寻找历史命令进行调用，也可以运行Get-History命令查找，用Invoke-History Id方式进行调用：
         - 如何记录PowerShell会话全文
         >如果想生成当前会话的记录，可以运行Start-Transcript命令，它基于当前系统时间。如果想停止，运行Stop-Transcript：
### 文件及文件夹操作
   * 官网参考
      + [Get-ChildItem](Get-ChildItem)<br>
   * 搜索文件夹及文件的方法
   * 批量拷贝复制文件和文件夹的操作
      + 参考
         - [How to Copy Folder Structure without Copying the Files](https://www.winhelponline.com/blog/how-to-copy-folder-structure-without-copying-files/)<br>
         - [Copying Directory Structures without Files](https://cects.com/copying-directory-structures-without-files/)<br>
         - [Windows: How to Copy Folder Structure without Copying Files](https://sumtips.com/software/windows-how-to-copy-folder-structure-without-copying-files/)<br>
         - []()<br>
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量重命名文件夹或文件的操作
      + 参考
         - [vbs + PowerShell 对文件批量重命名](https://zhuanlan.zhihu.com/p/76023063)<br>
         - [求助一个批量修改子文件夹中文件名的批处理文件](http://www.bathome.net/thread-53039-1-1.html) - 其中提供的vbs的方法<br>
         - [vbs批量修改文件名](https://blog.csdn.net/u011668104/article/details/52240018)<br>
   * 批量替换文件内容的操作
      + 参考
         - [How To Replace Text in a File with PowerShell](https://mcpmag.com/articles/2018/08/08/replace-text-with-powershell.aspx)<br>
         - [Using PowerShell to replace text in a file](https://adamtheautomator.com/powershell-replace-text-in-file/) - **重点**：以这篇文档为参考<br>
         - [Replace string in PowerShell Script using Get-Content and Set-Content](https://social.technet.microsoft.com/Forums/windowsserver/en-US/db2b9342-70b0-4577-b87c-d666f2b40c35/replace-string-in-powershell-script-using-getcontent-and-setcontent?forum=winserverpowershell)<br>
         - YouTube
            * [How To Replace Text In A File With PowerShell - YouTube](https://www.youtube.com/watch?v=VbtTCLFjr7w)<br>
      + 方法
         1. 直接替换文件内容 - 以 [Using PowerShell to replace text in a file](https://adamtheautomator.com/powershell-replace-text-in-file/) - **重点**：以这篇文档为参考
            - 步骤
            
         2. 建立临时文件，修改后，替换原来文件。注：替换前，重命名文件（更改文件名称），或复制一个。
            - 步骤
### 知识点总结
   * 如何传递参数（parameters）
      + 参考
         - [How do I pass in a string with spaces into PowerShell?](https://stackoverflow.com/questions/28311191/how-do-i-pass-in-a-string-with-spaces-into-powershell)<br>
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
   * 如何循环和流程控制(Looping and Flow Control)
      + 参考
         - [Windows PowerShell Cookbook, 3rd Edition by Lee Holmes](https://www.oreilly.com/library/view/windows-powershell-cookbook/9781449359195/ch04.html)<br>
         - [5 Flow control in scripts](https://livebook.manning.com/book/windows-powershell-in-action-third-edition/chapter-5/v-16/1) - 不错的书<br>
   * PowerShell functions
      + 参考
         - [The PowerShell parameter demystified](https://adamtheautomator.com/the-powershell-parameter/)<br>
   * PowerShell modules
      + 参考
         - [Use the PowerApps PowerShell modules to automate tasks with the Microsoft Power Platform](https://blog.atwork.at/post/Use-PowerApps-PowerShells)<br>
   * Automation of tasks with PowerShell
      + 参考
        - [Automation of tasks with PowerShell](https://docs.microsoft.com/en-us/power-platform/admin/wp-task-automation-powershell)<br>     
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
             PowerShell -file .\TestQuotes.ps1 -Config "A B C" - 用PowerShell 参数的方式不同：所有参数均包含在""中。 
         ```
         ```shell
             powershell -file test.ps1 -x "hello world" -y "my friend"
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
      3. 通过批处理文件执行PowerShell脚本的方法：- Create a .bat wrapper with the following content（因时间的关系，脚本执行结果有错，后续更正，但，方法就是这样的） 
         + 参考
            * [How to pass batch file variables to PowerShell script? [duplicate]](https://stackoverflow.com/questions/56961935/how-to-pass-batch-file-variables-to-powershell-script)<br>
         + 测试文件位置 - Sample 1
            * powershell文件：E:\Notes\4_ComputeCourse\PowerShell\Script\test1.ps1
            ```shell
               # test1.ps1
               param(
                   $x = "",
                   $y = ""
               )

               &echo $x $y
            ```
            * 批处理文件（bat)：E:\Notes\4_ComputeCourse\PowerShell\Script\test.bat
            ```shell
               @rem test.bat
               @powershell -file test.ps1 %1 %2 %3 %4
            ```
         + 命令格式 - And then call it:  - 调用命令的格式
         ```shell
             test.bat -x "hello world" -y "my friend"
         ```
         + 结果如下：
         ```shell
             E:\Notes\4_ComputeCourse\PowerShell\Script>test.bat -x "hello world" -y "my friend"
             hello world
             my friend
         ```
         + 测试文件位置 - Sample 2
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
         + 命令格式
         ```shell
             PowerShell -file .\TestQuotes.ps1 -Config "A B C" - 用PowerShell 参数的方式不同：所有参数均包含在""中。 
         ```
         ```shell
             powershell -file test.ps1 -x "hello world" -y "my friend"
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
### 高级操作
   * 参考
      + [Managing Microsoft PowerApps and Flow Like a Pro – Part 1](https://www.syskit.com/blog/managing-microsoft-powerapps-and-flow-like-a-pro-pt1/)<br>
      + [How to pass parameters to the script while calling them inside the provisioner](https://stackoverflow.com/questions/54587146/how-to-pass-parameters-to-the-script-while-calling-them-inside-the-provisioner) - provisioner 不知是啥，后续学习<br>
      + [Help with passing arguments with Invoke-Command (I am using -ArgumentList)](https://powershell.org/forums/topic/help-with-passing-arguments-with-invoke-command-i-am-using-argumentlist/)<br>
# Linux及MacOS
## Bash Shell script
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
