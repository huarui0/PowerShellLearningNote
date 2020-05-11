# Windows
## PowerShell & VBScript
   * 学习路线图
      + --> ... --> Function --> Provider --> ...-->Module...
   * 安装
      + 下载地址
         - [PowerShell/PowerShell](https://github.com/PowerShell/PowerShell)<br>
      + 参考
         - [Installing the ZIP package](https://docs.microsoft.com/en-us/powershell/scripting/install/installing-powershell-core-on-windows?view=powershell-7.x#zip)<br>
         - [Upgrading to PowerShell 7: A Walkthrough](https://adamtheautomator.com/updating-to-powershell-7/) - 有些复杂，但很专业，版本不是最新<br>
         - [HOW TO INSTALL AND UPDATE POWERSHELL 7](https://www.thomasmaurer.ch/2019/07/how-to-install-and-update-powershell-7/)<br>
         - [How to Install PowerShell 7](https://www.petri.com/how-to-install-powershell-7)<br>
   * 常用命令
      + 查版本及相关信息
      ```
      Get-Host | Select-Object Version
      $PSVersionTable
      ```
   * 参考
      + [PowerShell/docs/learning-powershell/](https://github.com/PowerShell/PowerShell/tree/master/docs/learning-powershell) - 官方指南，最权威。很棒的入门学习资料 - 详细的学习参见 微软官网 的 PowerShell 主页：[https://docs.microsoft.com/en-us/powershell](https://docs.microsoft.com/en-us/powershell)<br>
         - [PowerShell Beginner’s Guide](https://github.com/PowerShell/PowerShell/blob/master/docs/learning-powershell/powershell-beginners-guide.md)<br>
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
         - 判断文件夹是否为空 或 文件夹含有多少文件和文件夹的方法 - Google Search: powershell get childitem count items
            * 参考
               + [Measure-Object](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/measure-object?view=powershell-7.x)<br>
               + [How to Count Objects in PowerShell](https://www.itechguides.com/powershell-count/)<br>
               + [How to count the files in a folder using PowerShell, CMD, or File Explorer](https://www.digitalcitizen.life/4-ways-count-number-folders-and-files-inside-folder)<br>
                - ```bash
                      $ItemCount = (Get-ChildItem -Path $DesignatedFolder | Measure-Object).Count
                  ```
      + [Copy-Item](Copy-Item)<br>
      + [New-Item](New-Item)<br>
   * 搜索文件夹及文件的方法
      + 参考
      
      + Samples
         - 仅搜索文件夹的脚本
            1. 使用 -Dicrctory
            ```powershell
                Get-ChildItem -Directory 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Recurse | Where-Object -Property name -eq -Value 'build' | ForEach-Object -Parallel {Write-Host $_}
            ```
            2. 使用 -Attributes 'Directory' 也可以不用 '': -Attributes Directory
            ```powershell
                Get-ChildItem -Path 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Attributes Directory -Recurse | Where-Object -Property name -eq -Value 'build' | ForEach-Object -Parallel {Write-Host $_}
                # Another example
                Get-ChildItem -Directory .\ -Recurse | Where-Object -Property name -eq -Value 'openliberty' | ForEach-Object -Parallel {Write-Host $_}
            ```
         - 根据文件类型来搜索文件的方法
            1. 例子来自：[Rename multiple files in multiple folders with PowerShell](https://pantaley.com/blog/Rename-multiple-files-in-multiple-folders-with-PowerShell/)<br>
            ```powershell
                $path = "project\\test"
                $fileTypes = ".*.jpg|.*.bmp|.*.png|.*.gif|.*.tif"
                $files = Get-ChildItem -Recurse | Where-Object FullName -Match ".*$path*"
                $counter = 1
                $dir = ""

                foreach ($file in $files) {
                    $name = $file.Name
                    $fullname = $file.FullName
                    $extension = $file.Extension

                    if ($name -Match $fileTypes) {
                        if ($dir -ne $file.Directory.Name) {
                            $dir = $file.Directory.Name
                            $counter = 1
                        }

                        $zero = If ( $counter -le 9) { "0" } Else { "" }

                        Rename-Item $fullname "$zero$counter-$dir$extension"

                        $counter++
                    }
                }
            ```
         - 常用命令参考
            * 条件筛选的一些用法：[PowerShell Basics: Where | Where-Object filter {$_.property -eq statement}](https://www.computerperformance.co.uk/powershell/where/)<br>
            * 比较命令的运行效率（速度）的用法：[Measure-Object in PowerShell](https://www.computerperformance.co.uk/powershell/measure-object-filter/)<br>
            * 搜索：某个文件，如 aaa.java
               + ```shell
                     Get-ChildItem -Path "E:\JavaEESamples" -Recurse  | Where-Object -Property name -eq -Value "FruitResource.java" | ForEach-Object -Parallel {
                          Write-Host "`$_.Name = $_" -ForegroundColor DarkYellow
                     }
                 ```
            * 搜索：一个文件夹中，某种类型（*.json)的文件，但排除某个文件夹
               + ```shell
                     Get-ChildItem -Path .\AndroidX\*.json  -Recurse | Where-Object FullName -notmatch build >alljson.txt
                 ```
               + ```shell
                     gci "E:\AppDev" -Filter "*.properties" -recurse
                 ```
            * 常用条件搜索 - multi condition
               + ```shell
                     Get-ChildItem -Path "E:\JavaDev" -Recurse -Exclude "wrapper" | Where-Object {$_.extension -eq ".properties" -and $_.fullname -notlike "*wrapper*" -and $_.fullname -notlike "*.metadata*"} | ForEach-Object -Parallel {
                         Write-Host "`$_.Name = $_" -ForegroundColor DarkYellow
                     }
                 ```
   * 批量拷贝复制文件和文件夹的操作
      + 参考
         - [How to Copy Folder Structure without Copying the Files](https://www.winhelponline.com/blog/how-to-copy-folder-structure-without-copying-files/)<br>
         - [Copying Directory Structures without Files](https://cects.com/copying-directory-structures-without-files/)<br>
         - [Windows: How to Copy Folder Structure without Copying Files](https://sumtips.com/software/windows-how-to-copy-folder-structure-without-copying-files/)<br>
         - [Copy-Item: Copying Files like a Boss in PowerShell](https://adamtheautomator.com/copy-item-copying-files-powershell/) - 这篇文章介绍的方法蛮好的，可以参考学习<br>
         - [The Ultimate Guide to Robocopy](https://adamtheautomator.com/robocopy-the-ultimate/) - Windows系统的大量数据的工具<br>
            * >Robocopy is one of the most-used command-line utilities to copy large volumes of data in Windows. It's such a popular tool because of how powerful it is. But with all that power comes complexity. In this guide, we're going to break down all that complexity and provide a complete tutorial on using this useful tool.
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
### 
### 知识点总结
   * Understanding file encoding in VSCode and PowerShell
      + 官网参考
         - [Understanding file encoding in VSCode and PowerShell](https://docs.microsoft.com/en-us/powershell/scripting/components/vscode/understanding-file-encoding?view=powershell-7.x)<br>
            - If you need to re-encode multiple files, you can use the following script: - 设置命令
            ```powershell
                Get-ChildItem *.ps1 -Recurse | ForEach-Object {
                    $content = Get-Content -Path $_
                    Set-Content -Path $_.Fullname -Value $content -Encoding UTF8 -PassThru -Force
                }
            ```
      + [](https://blog.invivoo.com/how-to-solve-unicode-encoding-issues/)<br>
   * 配置文件（Profiles）- 与Function结合看，了解Function跟Profile的关系先
      + [About Profiles](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_profiles?view=powershell-7)<br>
         - >You can create a PowerShell profile to customize your environment and to add session-specific elements to every PowerShell session that you start.

         - >A PowerShell profile is a script that runs when PowerShell starts. You can use the profile as a logon script to customize the environment. You can add commands, aliases, functions, variables, snap-ins, modules, and PowerShell drives. You can also add other session-specific elements to your profile so they are available in every session without having to import or re-create them.
   * About Variables
      + About String
         - About NULL and EMPTY
            * 参考
               + [PowerShell - Testing if a String is NULL or EMPTY]() - **很好，参考这篇为准**<br>
               + [Powershell: How to check if a string is null or empty](https://cantyouautomatethat.com/powershell-check-if-string-null-or-empty/) - 也不错<br>
               + 参考下面 About Automatic Variables 中 About $null<br>
         - About Special Characters
            * 官网参考
               + [About Special Characters](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_special_characters?view=powershell-7.x)<br>
                  - Special parsing token:
                     * |Sequence|Description|
                       |:--------:|:-----------:|
                       |`--%`|Stop parsing anything that follows|
                  - Null (`0)
                     * >The null (\`0) character appears as an empty space in PowerShell output. This functionality allows you to use PowerShell to read and process text files that use null characters, such as string termination or record termination indicators. The null special character isn't equivalent to the `$null` variable, which stores a null value.
                  - Backspace (`b)
                     * >The backspace (\`b) character moves the cursor back one character, but it doesn't delete any characters.
                  - 变量符号 (`$)
                     * 变量符号`$`的原始输出
         - About Quoting Rules
            * 官网参考
               + [About Quoting Rules](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_quoting_rules?view=powershell-7.x)<br>
         - About String Comparison
            * 官网参考
               + [About Comparison Operators](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_comparison_operators?view=powershell-7.x)<br>
                  - Note
                     * > If you need to compare a value to `$null` you should put `$null` on the left-hand side of the comparison. When you compare `$null` to an **Object[]** the result is **False** because the comparison object is an array. When you compare an array to `$null`, the comparison filters out any `$null` values stored in the array. For example:
         - About join
            * 官网参考
               + [About join](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_join?view=powershell-7.x)<br>
      * About Array
   * About Automatic Variables
      + 官网参考
         - [About Automatic Variables](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_automatic_variables?view=powershell-7)<br>
            * About $null
               + >$null is an automatic variable that contains a null or empty value. You can use this variable to represent an absent or undefined value in commands and scripts.
                 >
                 >PowerShell treats $null as an object with a value, that is, as an explicit placeholder, so you can use $null to represent an empty value in a series of values.

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
         - [ForEach-Object](https://docs.microsoft.com/ja-jp/PowerShell/module/microsoft.powershell.core/foreach-object?view=powershell-7.x) - 重要：Parallel 的学习，并应用！<br>
            * 注意
               1. Within the script block, use the `$_` variable to represent the current object.
               2. Deference between `Script block` and `Operation statement`.
               3. About `Script block`:
               >The Begin script block, which is the value of the Begin parameter, runs before this cmdlet processes the first input object. The End script block, which is the value of the End parameter, runs after this cmdlet processes the last input object.
               4. About `Parallel running script block`:
               >Beginning with PowerShell 7.0, a third parameter set is available that runs each script block in parallel. There is a -ThrottleLimit parameter that limits the number of parallel scripts running at a time. As before, use the $_ variable to represent the current input object in the script block. Use the $using: keyword to pass variable references to the running script.
         - [About ForEach](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_foreach?view=powershell-7) - 没有Parallel，优先用 ForEach-Object<br>
      + 参考
         - [Windows PowerShell Cookbook, 3rd Edition by Lee Holmes](https://www.oreilly.com/library/view/windows-powershell-cookbook/9781449359195/ch04.html)<br>
         - [5 Flow control in scripts](https://livebook.manning.com/book/windows-powershell-in-action-third-edition/chapter-5/v-16/1) - 不错的书<br>
   * About Operators
      + 官方参考
         - [About Operators](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_operators?view=powershell-7.x)<br>
   * Comparison Operator
      + 官方参考
         - [About Comparison Operators](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_comparison_operators?view=powershell-7.x)<br>
         - [Where-Object](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/where-object?view=powershell-7.x)<br>
         - [Removing Objects from the Pipeline (Where-Object)](https://docs.microsoft.com/en-us/powershell/scripting/samples/removing-objects-from-the-pipeline--where-object-?view=powershell-7.x)<br>
            * >Due to parsing considerations, symbols such as <,>, and = are not used as comparison operators. Instead, comparison operators are comprised of letters. The basic comparison operators are listed in the following table.<br>
            * |Comparison Operator|Meaning|Example (returns true)|
              |:-------|:-------|:------|
              | -eq |	is equal to |	1 -eq 1 |
              | -ne |	Is not equal to	| 1 -ne 2 |
              | -lt |	Is less than |	1 -lt 2 |
              | -le |	Is less than or equal to |	1 -le 2 |
              | -gt |	Is greater than |	2 -gt 1 |
              | -ge |	Is greater than or equal to |	2 -ge 1 |
              | -like |	Is like (wildcard comparison for text) |	"file.doc" -like "f*.do?" |
              | -notlike |	Is not like (wildcard comparison for text) |	"file.doc" -notlike "p*.doc" |
              | -contains |	Contains |	1,2,3 -contains 1 |
              | -notcontains |	Does not contain |	1,2,3 -notcontains 4 |
   * about_Logical_Operators
      + 参考
         - [about_Logical_Operators](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_logical_operators?view=powershell-7.x)<br>
            * The standard logical operators are listed in the following table.
               + | Logical Operator | Meaning | Example (returns true) |
                 |--------|---------|---------------|
                 | -and	| Logical and; true if both sides are true |	(1 -eq 1) -and (2 -eq 2) |
                 |-or |	Logical or; true if either side is true |	(1 -eq 1) -or (1 -eq 2) |
                 |-not |	Logical not; reverses true and false |	-not (1 -eq 2) |
                 |! |	Logical not; reverses true and false |	!(1 -eq 2) |
   * About Operator Precedence
      + [About Operator Precedence](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_operator_precedence?view=powershell-7.x)<br>
   * Regular Expressions in PowerShell
      + 参考
         - [About Regular Expressions](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_regular_expressions?view=powershell-7)<br>
   * PowerShell functions
      + 官方参考
         - [About Functions](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions?view=powershell-7) - 重点看<br>
            * >A function is a named block of code that performs an action. When you type the function name, the code in the function runs. A filter is a named block of code that establishes conditions for an action. You can type the name of the filter in place of the condition, such as in a Where-Object command.
         - [Function provider](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_function_provider?view=powershell-7) - 必要看！<br>
            * >The PowerShell Function provider lets you get, add, change, clear, and delete the functions and filters in PowerShell.
         - [About Functions Advanced](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced?view=powershell-7)<br>
         - [About Functions Advanced Parameters](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_parameters?view=powershell-7)<br>
            * ParameterSetName argument
               + >The ParameterSetName argument specifies the parameter set to which a parameter belongs. If no parameter set is specified, the parameter belongs to all the parameter sets defined by the function. Therefore, to be unique, each parameter set must have at least one parameter that isn't a member of any other parameter set.
            * Switch parameters
               + 参考
                  - [How to pass a switch parameter to another PowerShell script?](https://config9.com/windows/powershell/how-to-pass-a-switch-parameter-to-another-powershell-script/)<br>
               + >Switch parameters are parameters with no parameter value. They're effective only when they're used and have only one effect.
                 >
                 >For example, the NoProfile parameter of powershell.exe is a switch parameter.
                 >
                 >To create a switch parameter in a function, specify the Switch type in the parameter definition.
               + >Switch parameters are easy to use and are preferred over Boolean parameters, which have a more difficult syntax.
                 >
                 >For example, to use a switch parameter, the user types the parameter in the command.
                 >
                 >`-IncludeAll`
                 >To use a Boolean parameter, the user types the parameter and a Boolean value.
                 >`-IncludeAll:$true`
                 >
                 >When creating switch parameters, choose the parameter name carefully. Be sure that the parameter name communicates the effect of the parameter to the user. Avoid ambiguous terms, such as Filter or Maximum that might imply a value is required.
         - [About Functions Advanced Methods](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_methods?view=powershell-7)<br>
         - [Approved Verbs for PowerShell Commands](https://docs.microsoft.com/en-us/powershell/scripting/developer/cmdlet/approved-verbs-for-windows-powershell-commands?view=powershell-7)<br>
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

      + **专题：如何从一个Function（User defined）调用另一个Function（User defined）**
         - 参考
            * [https://docs.microsoft.com/en-us/powershell/scripting/developer/module/importing-a-powershell-module?view=powershell-7.x](https://docs.microsoft.com/en-us/powershell/scripting/developer/module/importing-a-powershell-module?view=powershell-7.x) - 英文版<br>
            * [导入 PowerShell 模块](https://docs.microsoft.com/zh-cn/powershell/scripting/developer/module/importing-a-powershell-module?view=powershell-7.x) - 中文版<br>
            * [Calling a Function From Another Function in PowerShell](https://code5.cn/so/function/1274565) - InlineScript(PowerShell ScriptBlock) 的用法，不是最好的解决方法<br>
         - 其他参考
            * [Powershell学习笔记——函数和函数库](https://m.w3cschool.cn/xwevd/xwevd-6di3250s.html)<br>
            * [在PowerShell中，如何在文件中定义函数并从PowerShell命令行调用它？](https://www.itranslater.com/qa/details/2120503661221643264) - 倾向于使用其中的建议：Import-Module 的方法。。。<br>
      + **专题：如何将一个Function（User defined）的参数传递到另一个Function（User defined）**
         - 参考
            * [PowerShell函数中把参数传入另一个函数的函数传参例子，powershell](http://www.dengb.com/jcjc/854466.html) - 年代久远，但参数传递的方法可以借鉴，不用的清除掉先，然后传递。很好<br> 
            * [从 Pipeline 输入函数参数](https://www.cnblogs.com/sparkdev/p/8242167.html)<br>
            * [powershell脚本，命令行参数传值，并绑定变量的例子](https://www.cnblogs.com/piapia/p/5910255.html) - 了解 CmdletBinding 的使用方法的好例子<br>
   * Script Blocks - 了解与Function的区别
      + 参考
         - [About Script Blocks](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_script_blocks?view=powershell-7)<br>
   * About methods
      + 官网参考
         - [About methods](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_methods?view=powershell-7.x)<br>
            * >Objects have properties, which store data about the object, and methods that let you change the object.
              >
              >A "method" is a set of instructions that specify an action you can perform on the object. For example, the FileInfo object includes the CopyTo method that copies the file that the FileInfo object represents.
            * 获取一个对象的所有Method
               + To get the methods of any object, use the Get-Member cmdlet. Use its MemberType property with a value of "Method". The following command gets the methods of process objects.
               ```powershell
                   Get-Process | Get-Member -MemberType Method
               ```
   * About Comment Based Help

      + 官网参考
         - [About Comment Based Help](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_comment_based_help?view=powershell-7.x)<br>
   * PowerShell modules
      + 参考
         - [Use the PowerApps PowerShell modules to automate tasks with the Microsoft Power Platform](https://blog.atwork.at/post/Use-PowerApps-PowerShells)<br>
      + Samples
         - [drmohundro/Find-String](https://github.com/drmohundro/Find-String)<br>
         - >Find-String is a PowerShell script whose purpose is to emulate grep and/or ack. PowerShell already has the built-in Select-String cmdlet, but this script wraps Select-String and provides match highlighting on top of the searching capabilities.
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
### Powershell 的安全性 考虑
   * 参考
      - [浅谈powershell - 执行策略](https://www.jianshu.com/p/f728899ea71b)<br>
      - [针对日本的复杂Powershell恶意脚本分析](https://bbs.pediy.com/thread-251822.htm)<br>
### 高级操作
   * 参考
      + [Managing Microsoft PowerApps and Flow Like a Pro – Part 1](https://www.syskit.com/blog/managing-microsoft-powerapps-and-flow-like-a-pro-pt1/)<br>
      + [Building Asynchronous PowerShell Functions](https://adamtheautomator.com/building-asynchronous-powershell-functions/)<br>
      + [How to pass parameters to the script while calling them inside the provisioner](https://stackoverflow.com/questions/54587146/how-to-pass-parameters-to-the-script-while-calling-them-inside-the-provisioner) - provisioner 不知是啥，后续学习<br>
      + [Help with passing arguments with Invoke-Command (I am using -ArgumentList)](https://powershell.org/forums/topic/help-with-passing-arguments-with-invoke-command-i-am-using-argumentlist/)<br>
      + [Portable Modules](https://docs.microsoft.com/en-us/powershell/scripting/learn/writing-portable-modules?view=powershell-7.x) - 功能强大，后续学习<br>
      + [Running Remote Commands](https://docs.microsoft.com/en-us/powershell/scripting/learn/remoting/running-remote-commands?view=powershell-7.x)<br>
      + [Invoke-Command and Remote Variables](https://www.pdq.com/blog/invoke-command-and-remote-variables/)<br>
      + [Invoke-Command: The Best Way to Run Remote Code](https://adamtheautomator.com/invoke-command-remote/)<br>
      + [Invoke-Expression: The Universal PowerShell Executor Cmdlet (Lots of Examples)](https://adamtheautomator.com/invoke-expression/)<br>
      + [Sitecore PowerShell Extensions Remoting variables not working in Octopus Deploy](https://sitecore.stackexchange.com/questions/10757/sitecore-powershell-extensions-remoting-variables-not-working-in-octopus-deploy)<br>
      + [Splatting Parameters Pt 2 – Remote Possibilities](https://mjolinor.wordpress.com/2014/01/24/splatting-parameters-pt-2-remote-possibilities/)<br>
      + [Create your first PowerShell function in Azure](https://docs.microsoft.com/en-us/azure/azure-functions/functions-create-first-function-powershell)<br>

### PowerShell Module 的 实践
   * 官网参考
      - [Understanding a Windows PowerShell Module](https://docs.microsoft.com/en-us/powershell/scripting/developer/module/understanding-a-windows-powershell-module?view=powershell-7.x) - 准备知识学习<br>
      - [了解 Windows PowerShell 模块](https://docs.microsoft.com/zh-cn/powershell/scripting/developer/module/understanding-a-windows-powershell-module?view=powershell-7.x)<br>
      -
   * 实践步骤
      + 路线图
      ```
         编写 Windows PowerShell 模块 --> 如何编写 PowerShell 脚本模块 ---> [可选]如何编写 PowerShell 模块清单 --> 安装 PowerShell 模块 --> 导入 PowerShell 模块 --> 修改 PSModulePath 安装路径
                                    |
                                    --> 如何编写 PowerShell 二进制模块 --> 暂不考虑   
      ```
      + 步骤
         1. 编写 PowerShell 脚本模块
            - 位置
               * E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Manage-FolderTools.psm1
         2. 安装 PowerShell 模块
         3. 删除 PowerShell 模块
### Advanced functions 专题
   * 参考
      - [About Functions Advanced](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced?view=powershell-7)<br>
      - [About Functions Advanced Methods
](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_methods?view=powershell-7)<br>
      - [About Automatic Variables](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_automatic_variables?view=powershell-7) - 要仔细研究Function相关的部分内容。。。<br>
         1. $_
         2. $args
         3. $input
         4. $IsMacOS
         5. $IsWindows
         6. $PSBoundParameters - 这个很好，仔细研究
         7. $switch
         8. $this
         9. [Using Enumerators](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_automatic_variables?view=powershell-7#using-enumerators)<br>
      - [About Functions Advanced Parameters](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_functions_advanced_parameters?view=powershell-7) - 深入学习<br>
      - [About Hash Tables](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_hash_tables?view=powershell-7)<br>
# Linux及MacOS
## Bash Shell script
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
# 临时
## 正则表达式参考
   * [regex match nothing at the end of string](https://stackoverflow.com/questions/57454329/regex-match-nothing-at-the-end-of-string)<br>
      + `^\w*?\.(#+)(\.\w*?)*?$`
         - This regex matches any number of word characters (including none) in front of one dot, matches one or more octothorpe symbols, and then optionally matches a dot and more words/chars.      

      + ```shell
      
              ^\w*?\.(#+)(\.\w*?)*?$

              ^                         anchor to the start of the line
               \w*?                     get as many word characters as you want, but as few as you need
                   \.                   match . literally
                     (#+)               match one or more # literally. grouped for your convenience if you want to count how many times they appear or something.
                         (      )*?     match zero or more of this group:
                          \.            a literal dot...
                            \w*?        ...and zero or more word characters, as few as needed.
                                   $    ensure the string ends with this group.
