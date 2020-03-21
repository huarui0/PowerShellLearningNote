## Script: Test_Generate_FolderTool.ps1
# 

Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Manage-FolderTools.psm1 -Verbose

Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Show-Calendar.psm1 -Verbose

<#
    GetTemplateFolder
    GetSourceFolders foreach ($item in $collection) {
        Copy-Template-Folder to each Destination
    }
    # 1. 只拷贝文件夹，不包括文件夹下的内容
    方法：
    1. 拷贝完，更名: Rename a directory: Rename-Item -Path c:\a\cc -NewName dd
    2. 根据源文件夹
#>

# 1. 测试 - 在目的父文件夹下创建空的文件夹
# New-DestinationEmptyFolder -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true



# 2. 测试 - 将文件夹Copy到ParentFolder下，并更名 - 更名不能直接拷贝文件夹，用 -isCopyFolderContents 的方法 -- 不用也可以，但要有 -NewDistinationFolderName
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true -NewDistinationFolderName "S05.01-Solution-AsyncTaskLoader" -isCopyFolderContents:$true


# 测试 - 直接将文件夹Copy到ParentFolder下（不更名），- 正确！
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true

# 正式
# Remove-DesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true


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



    begin {
        # $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }

    process {
        try {

            Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\Modules\Show-Calendar.psm1 -Verbose


            Write-Host "`$DistinationParentFolder = $DistinationParentFolder"

            # 不能用 -Recurse
            Get-ChildItem -Path $SourceParentFolder | Where-Object { $_.PSIsContainer -eq $true } | ForEach-Object -Parallel {
                # "$using:Find $_"
                Write-Host "`$SourceParentFolder = $using:SourceParentFolder"
                Write-Host "`$_ = $_" -ForegroundColor Blue

                $NewDistinationFolderName =  Split-Path -Path $_ -Leaf 

# 步骤
# 1. 测试 - 在目的父文件夹下创建空的文件夹
# CreateDestinationEmptyFolder -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true

# 2. 测试 - 将文件夹Copy到ParentFolder下，并更名 - 更名不能直接拷贝文件夹，用 -isCopyFolderContents 的方法 -- 不用也可以，但要有 -NewDistinationFolderName
                # Copy-SourceFolderToDestination -TemplateFolder $TemplateFolder -SourceParentFolder $SourceParentFolder -DistinationParentFolder $DistinationParentFolder -ForceOverride:$false -NewDistinationFolderName $NewDistinationFolderName -isCopyFolderContents:$true
                # 测试的方法：先建一个文件夹，测试，会不会覆盖。结果：必须不会覆盖








            } -ThrottleLimit 4

        } catch {
            Write-Error $_.Exception.Message
        }
    }
}

Batch_CopySourceFolderToDestination -TemplateFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -SourceParentFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true -NewDistinationFolderName $NewDistinationFolderName -isCopyFolderContents:$true
