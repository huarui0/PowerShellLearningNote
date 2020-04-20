## Script: Test_Generate_FolderTool.ps1
# 

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
function New-DestinationEmptyFolder {
    <#
    .SYNOPSIS
        实现：
        1. 将源文件夹拷贝到指定的目标文件夹下（在目标文件夹下创建了新的文件夹）。
        2. 拷贝空的源文件夹（即：文件夹内容为空），然后将模板文件夹下的内容拷贝到该文件夹下。
    .PARAMETER SourceFolder
        The file path of the text file you'd like to perform a find/replace on.
    .PARAMETER DistinationParentFolder
    #>
    [CmdletBinding()]
    [OutputType()]
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$DistinationParentFolder,
        [Parameter()]
        [string]$DistinationFolder,
        [Parameter()]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$SourceFolder,
        [Parameter()]
        [string]$SourceFolderName,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceOverride
    )


        # [Parameter(Mandatory = $true)]
        # [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        # [string[]]$SourceFolders,


    begin {
        # $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }

    process {
        try {
            # 如果要拷贝的源文件夹所在的目标父文件夹存在
            if (Test-Path -Path $DistinationParentFolder -PathType 'Container') {
                
                # $SourceFolderName =  Split-Path -Path $SourceFolder -Leaf -Resolve
                $SourceFolderName =  Split-Path -Path $SourceFolder -Leaf 
                $DistinationFolder = Join-String -Separator '\' -InputObject $DistinationParentFolder, $SourceFolderName
                if (Test-Path -Path $SourceFolder -PathType 'Container') {

                    if (Test-Path -Path $DistinationParentFolder -PathType 'Container') {

                        if (Test-Path -Path $DistinationFolder -PathType 'Container') {

                            if ($ForceOverride) {

                            } else {
                                Write-Host "目标文件夹已存在[CreateDestinationEmptyFolder]: $DistinationFolder，继续..." -ForegroundColor Yellow
                            }

                        } else {


                            # 将文件夹的所有内容拷贝到目标文件夹下
                            # Copy-Item $SourceFolder $DistinationParentFolder -Recurse -Force

                            # 将文件夹结构拷贝到目标文件夹下 - 暂时 注释
                            ## Copy-Item $SourceFolder $DistinationParentFolder -Filter {PSIsContainer} -Recurse -Force

                            # 将顶层文件夹拷贝到目标文件夹下 - 以下两种方法都正确！！！
                            ## Copy-Item $SourceFolder $DistinationParentFolder -Filter {PSIsContainer} -Force

                            New-Item $DistinationFolder -type directory

                            # 将文件夹结构拷贝到目标文件夹下
                            # To flatten a file structure you can try:

                            # New-Item $DistinationParentFolder -type directory
                            # Get-ChildItem $SourceFolder -Recurse | `
                            #    Where-Object { $_.PSIsContainer -eq $False } | `
                            #    ForEach-Object {Copy-Item -Path $_.Fullname -Destination $SourceFolder -Force} 

                        }

                    }

                } else {

                }
            } else {

            }

        } catch {
            Write-Error $_.Exception.Message
        }
    }
}

# 1. 测试 - 在目的父文件夹下创建空的文件夹
# CreateDestinationEmptyFolder -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true

function Copy-FolderContents {
    <#
    .SYNOPSIS
        实现：
        1. 将源文件夹拷贝到指定的目标文件夹下（在目标文件夹下创建了新的文件夹）。
        2. 拷贝空的源文件夹（即：文件夹内容为空），然后将模板文件夹下的内容拷贝到该文件夹下。
    .PARAMETER SourceFolder
        The file path of the text file you'd like to perform a find/replace on.
    .PARAMETER DistinationParentFolder
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
        [Parameter()]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$SourceFolder,
        [Parameter()]
        [string]$SourceFolderName,
        [Parameter()]
        [string]$SourceFolderContents,
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

            # 因为如果是拷贝成新的文件夹名，不用isCopyFolderContents 也同样，所有放这里。
            $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

            if ($isCopyFolderContents) {

                # $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                # 将文件夹的所有内容拷贝到目标文件夹下
                Copy-Item $SourceFolderContents $DistinationFolder -Recurse -Force

            } else { # 如果是Copy后，更名为新的文件夹名，则需要用$isCopyFolderContents里的方法
                if ($NewDistinationFolderName) {
                    # 将文件夹的所有内容拷贝到目标文件夹下
                    Copy-Item $SourceFolderContents $DistinationFolder -Recurse -Force
                } else {
                    # 啰嗦，不要
                    # $SourceFolderContents = $SourceFolder
                    # Copy-Item $SourceFolderContents $DistinationParentFolder -Recurse -Force

                    # 将文件夹的所有内容拷贝到目标文件夹下 - 注意直接将源文件夹拷贝的父文件夹，即可！
                    Copy-Item $SourceFolder $DistinationParentFolder -Recurse -Force
                }
            }

        } catch {
            Write-Error $_.Exception.Message
        }
    }


}

function Copy-SourceFolderToDestination {
    <#
    .SYNOPSIS
        实现：
        1. 将源文件夹拷贝到指定的目标文件夹下（在目标文件夹下创建了新的文件夹）。
        2. 拷贝空的源文件夹（即：文件夹内容为空），然后将模板文件夹下的内容拷贝到该文件夹下。
    .PARAMETER SourceFolder
        The file path of the text file you'd like to perform a find/replace on.
    .PARAMETER DistinationParentFolder
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
        [Parameter()]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$SourceFolder,
        [Parameter()]
        [string]$SourceFolderName,
        [Parameter()]
        [string]$SourceFolderContents,
        [Parameter(Mandatory=$false)]
        [Switch]
        $isCopyFolderContents,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceOverride
    )

        # [Parameter(Mandatory = $true)]
        # [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        # [string[]]$SourceFolders,


    begin {
        # $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }

    process {

        try {
            # 如果要拷贝的源文件夹所在的目标父文件夹存在
            if (Test-Path -Path $DistinationParentFolder -PathType 'Container') {

                if (Test-Path -Path $SourceFolder -PathType 'Container') {
                    if ($NewDistinationFolderName) {
                        $DistinationFolder = Join-String -Separator '\' -InputObject $DistinationParentFolder, $NewDistinationFolderName
                    } else {

                        # $SourceFolderName =  Split-Path -Path $SourceFolder -Leaf -Resolve
                        $SourceFolderName =  Split-Path -Path $SourceFolder -Leaf

                        $DistinationFolder = Join-String -Separator '\' -InputObject $DistinationParentFolder, $SourceFolderName

                    }

                    # 移到子函数中。。。
                    # 因为如果是拷贝成新的文件夹名，不用isCopyFolderContents 也同样，所有放这里。
                    # $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                    if (Test-Path -Path $DistinationFolder -PathType 'Container') {
                        Write-Host "目标文件夹已存在: $DistinationFolder，继续..." -ForegroundColor Yellow

                        if ($ForceOverride) {
# SourceFolderContents 移到子函数中，故不加参数
                            Copy-FolderContents -SourceFolder $SourceFolder -NewDistinationFolderName $NewDistinationFolderName -DistinationFolder $DistinationFolder -DistinationParentFolder $DistinationParentFolder -isCopyFolderContents:$isCopyFolderContents

                        } else {

                            Write-Warning "The folder at '$DistinationFolder' exists and the -Force param was not used"

                            Write-Host "目标文件夹已存在,且未提供ForceOverride参数[CopySourceFolderToDestination]: $DistinationFolder，Stop..." -ForegroundColor Yellow
                        }


                    } else {  # 不存在，就先创建！！
                        Write-Host "目标文件夹不存在: $DistinationFolder ..." -ForegroundColor Red

                        # 先将顶层空文件夹拷贝到目标文件夹下 - 以下两种方法都正确！！！

                        Write-Host "先将顶层空文件夹拷贝到目标文件夹下: $DistinationFolder ..." -ForegroundColor Red

                        # Copy-Item $SourceFolder $DistinationParentFolder -Filter {PSIsContainer} -Force

                        # 上句不能用（需要将文件名用新的文件名 组成 新的 SourceFolder），要用下句
                        
                        New-Item $DistinationFolder -type directory

                        # Sub_CopySourceFolderToDestination -SourceFolder $SourceFolder -SourceFolderContents $SourceFolderContents -NewDistinationFolderName $NewDistinationFolderName -DistinationFolder $DistinationFolder -DistinationParentFolder $DistinationParentFolder -isCopyFolderContents:$isCopyFolderContents
# SourceFolderContents 移到子函数中，故不加参数
                        Copy-FolderContents -SourceFolder $SourceFolder -NewDistinationFolderName $NewDistinationFolderName -DistinationFolder $DistinationFolder -DistinationParentFolder $DistinationParentFolder -isCopyFolderContents:$isCopyFolderContents
                    }

                } else {
                    Write-Host "源文件夹不存在: $SourceFolder ..." -ForegroundColor Red
                }
            } else {
                 Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
            }

        } catch {
            Write-Error $_.Exception.Message
        }
    }
}


# 2. 测试 - 将文件夹Copy到ParentFolder下，并更名 - 更名不能直接拷贝文件夹，用 -isCopyFolderContents 的方法 -- 不用也可以，但要有 -NewDistinationFolderName
# CopySourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true -NewDistinationFolderName "S05.01-Solution-AsyncTaskLoader" -isCopyFolderContents:$true


# 测试 - 直接将文件夹Copy到ParentFolder下（不更名），- 正确！
# CopySourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# CopySourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true

function Remove-DesignatedFolderContents {
    <#
    .SYNOPSIS
        实现：
        1. 将源文件夹拷贝到指定的目标文件夹下（在目标文件夹下创建了新的文件夹）。
        2. 拷贝空的源文件夹（即：文件夹内容为空），然后将模板文件夹下的内容拷贝到该文件夹下。
    .PARAMETER SourceFolder
        The file path of the text file you'd like to perform a find/replace on.
    .PARAMETER DistinationParentFolder
    #>
    [CmdletBinding()]
    [OutputType()]
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        [string]$DesignatedFolder, # 指要删除内容的文件夹
        [Parameter()]
        [string]$DesignatedFolderName, #
        [Parameter()]
        [string]$FolderContents, # *：说明是全部，FolderName：仅删除指定的文件夹
        [Parameter(Mandatory=$false)]
        [Switch]
        $isDeleteFolderContents,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceDelete
    )


        # [Parameter(Mandatory = $true)]
        # [ValidateScript({Test-Path -Path $_ -PathType 'Container'})]
        # [string[]]$SourceFolders,


    begin {
        # $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }

    process {
        try {
            # 如果要拷贝的源文件夹所在的目标父文件夹存在
            if (Test-Path -Path $DesignatedFolder -PathType 'Container') {

                if ($isDeleteFolderContents) { # 仅删除文件夹的内容
                    $FolderContents = Join-String -Separator '\' -InputObject $DesignatedFolder, "*"

                    Remove-Item -Path $FolderContents  -Recurse  -Force

                    # 有问题，不能用 - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                    # Get-ChildItem -Path $DesignatedFolder -Recurse | Remove-Item $_ -Recurse -Force

<# 有问题，不能用  - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                    Get-ChildItem -Path $DesignatedFolder -Recurse | ForEach-Object -Parallel {
                        Remove-Item -Path $_  -Recurse  -Force
                    } -ThrottleLimit 4
#>
                } else { # 删除整个文件夹

                    if ($ForceDelete) {
                        Remove-Item -Path $DesignatedFolder -Recurse -Force
                    } else {
                        Write-Warning "The folder at '$DesignatedFolder' exists files or subfolders and the -ForceDelete param was not used"
                    }

                }

            } else {
                 Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
            }


        } catch {
            Write-Error $_.Exception.Message
        }
    }
}

# 正式
# DeleteDesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true

Export-ModuleMember -Function New-DestinationEmptyFolder,Copy-FolderContents,Copy-SourceFolderToDestination,Remove-DesignatedFolderContents