# Windows
## PowerShell & VBScript
   * 学习路线图
      + --> ... --> Function --> Provider --> ...
   * 安装
      + 参考
         - [Installing the ZIP package](https://docs.microsoft.com/en-us/powershell/scripting/install/installing-powershell-core-on-windows?view=powershell-7.x#zip)<br>
         - [Upgrading to PowerShell 7: A Walkthrough](https://adamtheautomator.com/updating-to-powershell-7/) - 有些复杂，但很专业，版本不是最新<br>
      + 下载地址
         - [PowerShell/PowerShell](https://github.com/PowerShell/PowerShell)<br>
   * 常用命令
      + 查版本及相关信息
      ```
      Get-Host | Select-Object Version
      $PSVersionTable
      ```
   * 参考
      + [PowerShell/docs/learning-powershell/](https://github.com/PowerShell/PowerShell/tree/master/docs/learning-powershell) - 官方指南，最权威。非常棒的入门学习资料<br>
      + [Starting Windows PowerShell](https://docs.microsoft.com/en-us/powershell/scripting/getting-started/starting-windows-powershell?view=powershell-7.x)<br>
      + [Powershell 编写和运行脚本](https://www.pstips.net/powershell-create-and-start-scripts.html) - 参考其中的几个方法<br>
      + [从零开始——PowerShell应用入门（全例子入门讲解）](https://www.cnblogs.com/lavender000/p/6935589.html)<br>
      + Youtube
         - [PowerShell String Manipulation](https://www.youtube.com/watch?v=-aQWrfqHbjU)<br>
            * 说明
               + 这个视频非常好，学完基础后，必要看
               + 可以学<br>
                  1. 条件的使用
                  2. 流程的控制
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
      + [Working with Files and Folders](https://docs.microsoft.com/en-us/powershell/scripting/samples/working-with-files-and-folders?view=powershell-7.x) - 参考这篇为准<br>
      + [Get-ChildItem](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/get-childitem?view=powershell-7.x) - 详细学习<br>
      + [Copy-Item](Copy-Item)<br>
      + [New-Item](New-Item)<br>
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
   * 批量添加文件内容的操作 - Add-Content 的方法 - 研究：是否可以当中插入内容？
   * 批量替换文件内容的操作
      + Google Search
         - `powershell how to replace text in file`
      + 参考
         - [How To Replace Text in a File with PowerShell](https://mcpmag.com/articles/2018/08/08/replace-text-with-powershell.aspx)<br>
         - [Using PowerShell to replace text in a file](https://adamtheautomator.com/powershell-replace-text-in-file/) - **重点**：以这篇文档为参考<br>
         - [Replacing Strings in Files Using PowerShell](https://www.itprotoday.com/powershell/replacing-strings-files-using-powershell) - 这篇也不错<br>
         - [Replace string in PowerShell Script using Get-Content and Set-Content](https://social.technet.microsoft.com/Forums/windowsserver/en-US/db2b9342-70b0-4577-b87c-d666f2b40c35/replace-string-in-powershell-script-using-getcontent-and-setcontent?forum=winserverpowershell)<br>
         - [String.Replace Method](https://docs.microsoft.com/en-us/dotnet/api/system.string.replace?view=netframework-4.8) - 参看其中的Examples<br>
         - YouTube
            * [How To Replace Text In A File With PowerShell - YouTube](https://www.youtube.com/watch?v=VbtTCLFjr7w)<br>
               + 备注
                  1. 注意方法的不同：().replace 括弧中是 String，方法用到 String.replace 的方法 查询的方式：$Varible | Get-Number 参考：String.Replace Method
                  2. 顺带学习 Regular Expression
      + 方法
         1. 直接替换文件内容 - 以 [Using PowerShell to replace text in a file](https://adamtheautomator.com/powershell-replace-text-in-file/) - **重点**：以这篇文档为参考
            - 测试文件位置 - Sample 1
               * powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\ReplaceFileContent.ps1
               * 测试用例文件：E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt
            - 步骤
               1. To read this file, you can use the Get-Content command.
               ```shell
                   $content = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt '
               ```
               2. Finding and Replacing the String
               ```shell
                   PS> $newContent = $content -replace 'foo', 'bar'
                   bar bar baz
               ```
               3. Writing to the File
               ```shell
                  $newContent | Set-Content -Path 'C:\file.txt'
               ```
               4. 
         2. 建立临时文件，修改后，替换原来文件。注：替换前，重命名文件（更改文件名称），或复制一个。
            - 步骤
            
               * powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\ReplaceFileContentByTmpFile.ps1
               * 测试用例文件：E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_by_tmp.txt
            - 步骤
               1. 
               ```shell
                   $filePath = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_by_tmp.txt'
               ```
               2. 
               ```shell
                   $tempFilePath = "$env:TEMP\$($filePath | Split-Path -Leaf)"
               ```
               3. To read this file, you can use the Get-Content command., Finding and Replacing the String, then Writing to the TMP File
               ```shell
                  $find = 'foo'
                  $replace = 'bar tmp'
 
                  (Get-Content -Path $filePath) -replace $find, $replace | Add-Content -Path $tempFilePath
               ```
               4. 
               ```shell
                   Remove-Item -Path $filePath
                   Move-Item -Path $tempFilePath -Destination $filePath
               ```
### 知识点总结
   * 配置文件（Profiles）- 与Function结合看，了解Function跟Profile的关系先
      + [About Profiles](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_profiles?view=powershell-7)<br>
         - >You can create a PowerShell profile to customize your environment and to add session-specific elements to every PowerShell session that you start.

         - >A PowerShell profile is a script that runs when PowerShell starts. You can use the profile as a logon script to customize the environment. You can add commands, aliases, functions, variables, snap-ins, modules, and PowerShell drives. You can also add other session-specific elements to your profile so they are available in every session without having to import or re-create them.
   * 如何传递参数（parameters）
      + 参考
         - [How do I pass in a string with spaces into PowerShell?](https://stackoverflow.com/questions/28311191/how-do-i-pass-in-a-string-with-spaces-into-powershell)<br>
         - [About Scopes](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_scopes?view=powershell-7)<br>
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
      + 官方参考
         - [ForEach-Object](https://docs.microsoft.com/ja-jp/PowerShell/module/microsoft.powershell.core/foreach-object?view=powershell-7.x)<br>
         - [About ForEach](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_foreach?view=powershell-7)<br>
            * 注意
               1. Within the script block, use the `$_` variable to represent the current object.
               2. Deference between `Script block` and `Operation statement`.
               3. About `Script block`:
               >The Begin script block, which is the value of the Begin parameter, runs before this cmdlet processes the first input object. The End script block, which is the value of the End parameter, runs after this cmdlet processes the last input object.
               4. About `Parallel running script block`:
               >Beginning with PowerShell 7.0, a third parameter set is available that runs each script block in parallel. There is a -ThrottleLimit parameter that limits the number of parallel scripts running at a time. As before, use the $_ variable to represent the current input object in the script block. Use the $using: keyword to pass variable references to the running script.
      + 参考
         - [Windows PowerShell Cookbook, 3rd Edition by Lee Holmes](https://www.oreilly.com/library/view/windows-powershell-cookbook/9781449359195/ch04.html)<br>
         - [5 Flow control in scripts](https://livebook.manning.com/book/windows-powershell-in-action-third-edition/chapter-5/v-16/1) - 不错的书<br>
   * PowerShell functions
      + 官方参考
         - [About Functions](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions?view=powershell-7) - 重点看<br>
            * >A function is a named block of code that performs an action. When you type the function name, the code in the function runs. A filter is a named block of code that establishes conditions for an action. You can type the name of the filter in place of the condition, such as in a Where-Object command.
         - [Function provider](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_function_provider?view=powershell-7) - 必要看！<br>
            * >The PowerShell Function provider lets you get, add, change, clear, and delete the functions and filters in PowerShell.
         - [About Functions Advanced](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced?view=powershell-7)<br>
         - [About Functions Advanced Parameters](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_parameters?view=powershell-7)<br>
         - [About Functions Advanced Methods](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_methods?view=powershell-7)<br>
      + 参考
         - [The PowerShell parameter demystified](https://adamtheautomator.com/the-powershell-parameter/)<br>
         - [PowerShell Function: Syntax, Parameters, Examples](https://www.itechguides.com/powershell-function-syntax-parameters-examples/)<br>
      + Funtion 格式（写法）
         - 测试文件位置
            * Sample 1
               + 参考
                  - [Powershell Functions Introduction](https://adamtheautomator.com/powershell-functions/)<br>
                  - [PowerShell Functions](https://www.javatpoint.com/powershell-functions) - 调用的例子<br>
               + powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\function_test1.ps1
               ```shell

                  function HelloWorld {
                      Write-Host 'Hello world!'
                  }

                  function Get-Square([int]$x) {
                      $res = $x * $x
                      return $res
                  }


                  # cls
                  HelloWorld

                  $x = Read-Host 'Enter a value'
                  $sqres = Get-Square $x
                  Write-Output "$x * $x = $sqres"

               ```
            * Sample 2
               + powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\test1.ps1
      + 执行方式
         1. 在脚本中直接调用- 例子见 Sample 1.  - 执行方法参照 【脚本执行模式】
      
      
      
   * Script Blocks - 了解与Function的区别
      + 参考
         - [About Script Blocks](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_script_blocks?view=powershell-7)<br>
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
             PS E:\Notes\4_LearningNotes\PowerShell\Script>  .\TestQuotes.ps1 -Config 'A B C' -Country '中国’
             Config = A B C
             Country = 中国
            ```
      2. Use only the PowerShell without -file argument
         + 命令格式
         ```shell
             PowerShell .\TestQuotes.ps1 -Config "A B C" - 用PowerShell 参数的方式不同：所有参数均包含在""中。 
         ```
         ```shell
             E:\Notes\4_LearningNotes\PowerShell\Script>powershell .\test1.ps1 -x 'hello world' -y 'my friend'
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
             PS E:\Notes\4_LearningNotes\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"
             Config = A
             Country = B
             PS E:\Notes\4_LearningNotes\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"  -Country '中国'
             Config = A
             Country = 中国
         ```
      3. 通过批处理文件执行PowerShell脚本的方法：- Create a .bat wrapper with the following content（因时间的关系，脚本执行结果有错，后续更正，但，方法就是这样的） 
         + 参考
            * [How to pass batch file variables to PowerShell script? [duplicate]](https://stackoverflow.com/questions/56961935/how-to-pass-batch-file-variables-to-powershell-script)<br>
         + 测试文件位置 - Sample 1
            * powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\test1.ps1
            ```shell
               # test1.ps1
               param(
                   $x = "",
                   $y = ""
               )

               &echo $x $y
            ```
            * 批处理文件（bat)：E:\Notes\4_LearningNotes\PowerShell\Script\test.bat
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
             E:\Notes\4_LearningNotes\PowerShell\Script>test.bat -x "hello world" -y "my friend"
             hello world
             my friend
         ```
         + 测试文件位置 - Sample 2
            * powershell文件：E:\Notes\4_LearningNotes\PowerShell\Script\updateTool.ps1
            ```shell
               ## Script: UpdateTool.ps1
               # 
               param(
                   [String]$LOG_FILE = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\default.log',
                   [String]$oName = 'default name',
                   [String]$oStart = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
                   [Int]$oStatus = 0,
                   [String]$oEnd = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
                   [String]$oDateRan = $(Get-date -F "yyyyMMdd"),
                   $DebugPreference = "SilentlyContinue"
               )

               "This is $oName"
            ```
            * 批处理文件（bat)：E:\Notes\4_LearningNotes\PowerShell\Script\Test_BatchFile_updateTool.bat
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
             PS E:\Notes\4_LearningNotes\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"
             Config = A
             Country = B
             PS E:\Notes\4_LearningNotes\PowerShell\Script> PowerShell .\TestQuotes.ps1 -Config "A B C"  -Country '中国'
             Config = A
             Country = 中国
         ```

### 高级操作
   * 参考
      + [Managing Microsoft PowerApps and Flow Like a Pro – Part 1](https://www.syskit.com/blog/managing-microsoft-powerapps-and-flow-like-a-pro-pt1/)<br>
      + [How to pass parameters to the script while calling them inside the provisioner](https://stackoverflow.com/questions/54587146/how-to-pass-parameters-to-the-script-while-calling-them-inside-the-provisioner) - provisioner 不知是啥，后续学习<br>
      + [Help with passing arguments with Invoke-Command (I am using -ArgumentList)](https://powershell.org/forums/topic/help-with-passing-arguments-with-invoke-command-i-am-using-argumentlist/)<br>
      + [Portable Modules](https://docs.microsoft.com/en-us/powershell/scripting/learn/writing-portable-modules?view=powershell-7.x) - 功能强大，后续学习<br>
      + [Running Remote Commands](https://docs.microsoft.com/en-us/powershell/scripting/learn/remoting/running-remote-commands?view=powershell-7.x)<br>
      + [Create your first PowerShell function in Azure](https://docs.microsoft.com/en-us/azure/azure-functions/functions-create-first-function-powershell)<br>
# Linux及MacOS
## Bash Shell script
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
