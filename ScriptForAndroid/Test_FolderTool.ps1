## Script: Test_Generate_FolderTool.ps1
# 

Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Manage-FolderTools.psm1 -Verbose



function Batch_CopySourceFolderToDestination {
    <#
    .SYNOPSIS
        实现在指定的文件或批量文件（名称相同）中，查找和替换文本的功能。
    .PARAMETER FilePaths
        The file path of the text file you'd like to perform a find/replace on.
    .PARAMETER FilePath
        The file path of the text file you'd like to perform a find/replace on.
        .PARAMETER OriginalFilePath
        该变量 用来实现将源文件（原始文件），覆盖到指定的文件夹中相同名称的文件，根据 $Force 变量判断是否覆盖原文件。

    .PARAMETER FileName
        The file name of the text file you'd like to perform a find/replace on.
    .PARAMETER Find
        The string you'd like to replace.
    .PARAMETER Replace
        The string you'd like to replace your 'Find' string with.
    .PARAMETER NewFilePath
        If a new file with the replaced the string needs to be created instead of replacing
        the contents of the existing file use this param to create a new file.
    .PARAMETER Force
        If the NewFilePath param is used using this param will overwrite any file that
        exists in NewFilePath.
    #>
    [CmdletBinding()]
    [OutputType()]
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$DistinationParentFolder,
        [Parameter()]
        [string]$DistinationFolder, # 在DistinationParentFolder下加NewDistinationFolderName
        [Parameter()]
        [string]$NewDistinationFolderName,
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$SourceParentFolder,
        [Parameter()]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$SourceFolder,
        [Parameter()]
        [string]$SourceFolderName,
        [Parameter()]
        [string]$SourceFolderContents,
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$TemplateFolder,
        [Parameter(Mandatory=$false)]
        [Switch]
        $isCopyFolderContents,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceOverride
    )




        try {

            Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Show-Calendar.psm1 -Verbose

            Write-Host "`$DistinationParentFolder = $DistinationParentFolder"

            # 不能用 -Recurse
            Get-ChildItem -Path $SourceParentFolder | Where-Object { $_.PSIsContainer -eq $true } | ForEach-Object -Parallel {
                # "$using:Find $_"
                Write-Host "`$SourceParentFolder = $using:SourceParentFolder"
                Write-Host "`$_ = $_" -ForegroundColor Blue

                $NewDistinationFolderName =  Split-Path -Path $_ -Leaf 
                Show-Calendar
                
            } -ThrottleLimit 4

        } catch {
            Write-Error $_.Exception.Message
        }

}

Batch_CopySourceFolderToDestination -TemplateFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -SourceParentFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true -NewDistinationFolderName $NewDistinationFolderName -isCopyFolderContents:$true
