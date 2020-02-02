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
   * 如何流程和循环控制
   
### 高级操作
   * 参考
      + [Managing Microsoft PowerApps and Flow Like a Pro – Part 1](https://www.syskit.com/blog/managing-microsoft-powerapps-and-flow-like-a-pro-pt1/)<br>
# Linux及MacOS
## Bash Shell script
### 文件及文件夹操作
   * 搜索文件夹及文件的方法
   * 批量删除文件及文件夹的操作
   * 批量替换文件及文件夹的操作
   * 批量移动文件夹和文件的操作
   * 批量替换文件内容的操作
