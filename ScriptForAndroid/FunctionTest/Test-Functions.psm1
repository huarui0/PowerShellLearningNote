## Script: Test_Generate_FolderTool.ps1
# 

<#
 GetTemplateFolder
GetSourceFolders foreach ($item in $collection) {
    Copy-Template-Folder to each Destination
}

#>

function Send-Greeting {
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory=$true)]
        [string] $Name
    )

    Process
    {
        Write-Host ("Hello " + $Name + "!")
        $a = "one", $null, "three"
        $a.count
    }
}

Export-ModuleMember -Function Send-Greeting
function Test1 {
    param($a, $b)
 
    # Display the parameters in dictionary format.
    $PSBoundParameters
 }

 function Test2 {
    param($a, $b)
 
    # Run the Test1 function with $a and $b.
    Test1 @PSBoundParameters
 }

 Export-ModuleMember -Function Test2

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

Export-ModuleMember -Function New-DestinationEmptyFolder

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

Export-ModuleMember -Function Copy-FolderContents

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

Export-ModuleMember -Function Copy-SourceFolderToDestination

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

Export-ModuleMember -Function Remove-DesignatedFolderContents


# 正式
# Remove-DesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true


function Copy-BatchSourceFolderToDestination {
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
        $ForceOverride,
        # 要删除的指定的文件夹
        [Parameter()]
        [string]$DesignatedFolder,
        [Parameter(Mandatory=$false)]
        [Switch]
        $isDeleteFolderContents,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceDelete
    )

    begin {
        # $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }

    process {
        try {

            Write-Host "`$DistinationParentFolder = $DistinationParentFolder"

            # 不能用 -Recurse
            Get-ChildItem -Path $SourceParentFolder | Where-Object { $_.PSIsContainer -eq $true } | ForEach-Object -Parallel {
                # "$using:Find $_"
                Write-Host "`$using:SourceParentFolder = $using:SourceParentFolder"
                Write-Host "`$using:DistinationParentFolder = $using:DistinationParentFolder"
                Write-Host "`$using:TemplateFolder = $using:TemplateFolder"

                Write-Host "`$using:_ = $using:_" -ForegroundColor Green
                Write-Host "`$_ = $_" -ForegroundColor Blue

                $FolderName = $_.NameString
                Write-Host "`$FolderName_String = $FolderName" -ForegroundColor DarkMagenta

                $FolderName = $_.Name
                Write-Host "`$FolderName = $FolderName" -ForegroundColor DarkYellow

                $NewDistinationFolderName =  Split-Path -Path $_ -Leaf 
                Write-Host "`$NewDistinationFolderName = $NewDistinationFolderName" -ForegroundColor DarkCyan

                Write-Host "`$PSBoundParameters(不能用@,只能用$) = $PSBoundParameters"  -ForegroundColor Green
            
# 步骤
# 1. 测试 - 在目的父文件夹下创建空的文件夹
# CreateDestinationEmptyFolder -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true

# 2. 测试 - 将文件夹Copy到ParentFolder下，并更名 - 更名不能直接拷贝文件夹，用 -isCopyFolderContents 的方法 -- 不用也可以，但要有 -NewDistinationFolderName
                # Copy-SourceFolderToDestination -TemplateFolder $TemplateFolder -SourceParentFolder $SourceParentFolder -DistinationParentFolder $DistinationParentFolder -ForceOverride:$false -NewDistinationFolderName $NewDistinationFolderName -isCopyFolderContents:$true
                # 测试的方法：先建一个文件夹，测试，会不会覆盖。结果：必须不会覆盖
                # Copy-SourceFolderToDestination $PSBoundParameters

# 2. 测试 - 将文件夹Copy到ParentFolder下，并更名 - 更名不能直接拷贝文件夹，用 -isCopyFolderContents 的方法 -- 不用也可以，但要有 -NewDistinationFolderName
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$true -NewDistinationFolderName "S05.01-Solution-AsyncTaskLoader" -isCopyFolderContents:$true

# Copy-SourceFolderToDestination 开始！！！
                $SourceFolder = $using:TemplateFolder
                Write-Host "`$SourceFolder = $SourceFolder"  -ForegroundColor Green

                if (Test-Path -Path $using:DistinationParentFolder -PathType 'Container') {

                    if (Test-Path -Path $SourceFolder -PathType 'Container') {
                        if ($NewDistinationFolderName) {
                            $DistinationFolder = Join-String -Separator '\' -InputObject $using:DistinationParentFolder, $NewDistinationFolderName
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
    
                            if ($using:ForceOverride) {
    # SourceFolderContents 移到子函数中，故不加参数
                                # Copy-FolderContents -SourceFolder $SourceFolder -NewDistinationFolderName $NewDistinationFolderName -DistinationFolder $DistinationFolder -DistinationParentFolder $DistinationParentFolder -isCopyFolderContents:$isCopyFolderContents
    

                                # 因为如果是拷贝成新的文件夹名，不用isCopyFolderContents 也同样，所有放这里。
                                $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                                if ($using:isCopyFolderContents) {

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


# 创建后，立即将 "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" 下面的文件夹内容删除。不要放在后面，以防误删！！！


# 正式
# Delete-DesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true

# Delete-DesignatedFolderContents 开始！！！
                                $DesignatedFolder = Join-String -Separator '\' -InputObject $DistinationFolder, "app\src\main"
                                Write-Host "`$DesignatedFolder = $DesignatedFolder"  -ForegroundColor Green


                                # 如果要拷贝的源文件夹所在的目标父文件夹存在
                                if (Test-Path -Path $DesignatedFolder -PathType 'Container') {

                                    if ($using:isDeleteFolderContents) { # 仅删除文件夹的内容
                                        $FolderContents = Join-String -Separator '\' -InputObject $DesignatedFolder, "*"

                                        Remove-Item -Path $FolderContents  -Recurse  -Force

# position first
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true

# Copy-DesignatedFolderContents 开始！！！

                                        $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                        Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position first]"  -ForegroundColor Green

                                        if (Test-Path -Path $SourceFolder -PathType 'Container') {
                                            $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                                            # 将文件夹的所有内容拷贝到目标文件夹下
                                            Copy-Item $SourceFolderContents $DesignatedFolder -Recurse -Force
                                        } else {
                                            Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position first]"
                                        }
# Copy-DesignatedFolderContents 结束！！！


                                        # 有问题，不能用 - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                                        # Get-ChildItem -Path $DesignatedFolder -Recurse | Remove-Item $_ -Recurse -Force

                                <# 有问题，不能用  - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                                        Get-ChildItem -Path $DesignatedFolder -Recurse | ForEach-Object -Parallel {
                                            Remove-Item -Path $_  -Recurse  -Force
                                        } -ThrottleLimit 4
                                #>
                                    } else { # 删除整个文件夹

                                        if ($using:ForceDelete) {
                                            Remove-Item -Path $DesignatedFolder -Recurse -Force

# position second
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true

# Copy-DesignatedFolderContents 开始！！！
# 这里Copy 文件夹 方式有错，要设置成ParentDesignatedFolder!!!!!!
                                            $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                            Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position second]"  -ForegroundColor Green

                                            if (Test-Path -Path $SourceFolder -PathType 'Container') {

                                                # 将文件夹的所有内容拷贝到目标文件夹下
                                                Copy-Item $SourceFolder $DesignatedFolder -Recurse -Force
                                            } else {
                                                Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position second]"
                                            }

# Copy-DesignatedFolderContents 结束！！！




                                        } else {
                                            Write-Warning "The folder at '$DesignatedFolder' exists files or subfolders and the -ForceDelete param was not used"
                                        }

                                    }

                                } else {
                                    Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
                                }

# Delete-DesignatedFolderContents 结束！！！

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
                            # Copy-FolderContents -SourceFolder $SourceFolder -NewDistinationFolderName $NewDistinationFolderName -DistinationFolder $DistinationFolder -DistinationParentFolder $DistinationParentFolder -isCopyFolderContents:$isCopyFolderContents





                            # 因为如果是拷贝成新的文件夹名，不用isCopyFolderContents 也同样，所有放这里。
                            $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                            if ($using:isCopyFolderContents) {

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


# 创建后，立即将 "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" 下面的文件夹内容删除。不要放在后面，以防误删！！！


# 正式
# Delete-DesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true

# Delete-DesignatedFolderContents 开始！！！
                            $DesignatedFolder = Join-String -Separator '\' -InputObject $DistinationFolder, "app\src\main"
                            Write-Host "`$SourceFolder = $DesignatedFolder"  -ForegroundColor Green


                            # 如果要拷贝的源文件夹所在的目标父文件夹存在
                            if (Test-Path -Path $DesignatedFolder -PathType 'Container') {

                                if ($using:isDeleteFolderContents) { # 仅删除文件夹的内容
                                    $FolderContents = Join-String -Separator '\' -InputObject $DesignatedFolder, "*"

                                    Remove-Item -Path $FolderContents  -Recurse  -Force

# position third
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true


# Copy-DesignatedFolderContents 开始！！！


                                    $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                    Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position third]"  -ForegroundColor Green

                                    if (Test-Path -Path $SourceFolder -PathType 'Container') {
                                        $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                                        # 将文件夹的所有内容拷贝到目标文件夹下
                                        Copy-Item $SourceFolderContents $DesignatedFolder -Recurse -Force
                                    } else {
                                        Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position third]"
                                    }


# Copy-DesignatedFolderContents 结束！！！








                                    # 有问题，不能用 - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                                    # Get-ChildItem -Path $DesignatedFolder -Recurse | Remove-Item $_ -Recurse -Force

                            <# 有问题，不能用  - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                                    Get-ChildItem -Path $DesignatedFolder -Recurse | ForEach-Object -Parallel {
                                        Remove-Item -Path $_  -Recurse  -Force
                                    } -ThrottleLimit 4
                            #>
                                } else { # 删除整个文件夹

                                    if ($using:ForceDelete) {
                                        Remove-Item -Path $DesignatedFolder -Recurse -Force

# position four
# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true

# Copy-DesignatedFolderContents 开始！！！
# 这里Copy 文件夹 方式有错，要设置成ParentDesignatedFolder!!!!!!
                                        $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                        Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position four]"  -ForegroundColor Green

                                        if (Test-Path -Path $SourceFolder -PathType 'Container') {

                                            # 将文件夹的所有内容拷贝到目标文件夹下
                                            Copy-Item $SourceFolder $DesignatedFolder -Recurse -Force
                                        } else {
                                            Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position four]"
                                        }

# Copy-DesignatedFolderContents 结束！！！

                                    } else {
                                        Write-Warning "The folder at '$DesignatedFolder' exists files or subfolders and the -ForceDelete param was not used"
                                    }

                                }

                            } else {
                                Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
                            }

# Delete-DesignatedFolderContents 结束！！！

                        }
    
                    } else {
                        Write-Host "源文件夹不存在: $SourceFolder ..." -ForegroundColor Red
                    }
                } else {
                     Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
                }
    
# Copy-SourceFolderToDestination 结束！！！




# 创建后，如果是强制删除标记为$true,则将 "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" 下面的文件夹内容强制删除。在非常确定的情况下，才可以$true，以防误删！！！


# 正式
# Delete-DesignatedFolderContents -DesignatedFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -isDeleteFolderContents:$true -ForceDelete:$true

# 最后：Delete-DesignatedFolderContents 开始！！！

                $DesignatedFolder = Join-String -Separator '\' -InputObject $DistinationFolder, "app\src\main"
                Write-Host "最后部分：`$DesignatedFolder = $DesignatedFolder"  -ForegroundColor Green


                # 如果要拷贝的源文件夹所在的目标父文件夹存在
                if (Test-Path -Path $DesignatedFolder -PathType 'Container') {

                    $ItemCount = (Get-ChildItem -Path $DesignatedFolder | Measure-Object).Count
                    Write-Host "`$ItemCount = $ItemCount" -ForegroundColor Green

                    if ($ItemCount -gt 0) {

                        if ($using:isDeleteFolderContents) { # 仅删除文件夹的内容
                            $FolderContents = Join-String -Separator '\' -InputObject $DesignatedFolder, "*"
                            Write-Host "最后部分[`$isDeleteFolderContents=`$true]：`$FolderContents = $FolderContents"  -ForegroundColor DarkGreen

                    # 这个地方没有明式声明$ForceDelete，不能删除
                            if ($using:ForceDelete) {
                                Remove-Item -Path $FolderContents  -Recurse  -Force

# position five

# 写在每个删除命令之后，立即重新拷贝即可。 - 已经存在的，如果不是强制删除，无法确定是什么情况，故不重新拷贝。triage 的情况，请见 Test-Funcions_Test_Triaged.ps1 用分别处理的方式(思想)。

# 正式-第二种 - 拷贝内容到文件夹下（不更名），-正确，注意：DistinationParentFolder都是ParentFolder
# Copy-SourceFolderToDestination -SourceFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src\main" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine\S05.01-Solution-AsyncTaskLoader\app\src" -ForceOverride:$true -isCopyFolderContents:$true


# Copy-DesignatedFolderContents 开始！！！

                                $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position five]"  -ForegroundColor Green

                                if (Test-Path -Path $SourceFolder -PathType 'Container') {
                                    $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                                    # 将文件夹的所有内容拷贝到目标文件夹下
                                    Copy-Item $SourceFolderContents $DesignatedFolder -Recurse -Force
                                } else {
                                    Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position five]"
                                }

# Copy-DesignatedFolderContents 结束！！！


                            } else {
                                Write-Warning "[Part of `$isDeleteFolderContents=`$True]. The folder at '$DesignatedFolder' exists files or subfolders and the -ForceDelete param was not used"
                            }

                            # 有问题，不能用 - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                            # Get-ChildItem -Path $DesignatedFolder -Recurse | Remove-Item $_ -Recurse -Force

                    <# 有问题，不能用  - 是不是 Get-ChildItem 中的 -Recurse 不要， Remove-Item 的 -Recurse 就可以了。 --> 待验证
                            Get-ChildItem -Path $DesignatedFolder -Recurse | ForEach-Object -Parallel {
                                Remove-Item -Path $_  -Recurse  -Force
                            } -ThrottleLimit 4
                    #>
                        } else { # 删除整个文件夹

                            if ($using:ForceDelete) {
                                Remove-Item -Path $DesignatedFolder -Recurse -Force

# posion six
# Copy-DesignatedFolderContents 开始！！！

                                $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                                Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position six]"  -ForegroundColor Green

                                if (Test-Path -Path $SourceFolder -PathType 'Container') {

                                    # 将文件夹的所有内容拷贝到目标文件夹下
                                    Copy-Item $SourceFolder $DesignatedFolder -Recurse -Force
                                } else {
                                    Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position six]"
                                }


# Copy-DesignatedFolderContents 结束！！！




                            } else {
                                Write-Warning "The folder at '$DesignatedFolder' exists files or subfolders and the -ForceDelete param was not used"



                            }

                        }

                    } else {
                        Write-Warning "The folder at '$DesignatedFolder' 为空！"

# position seven
# Copy-DesignatedFolderContents 开始！！！

                        $SourceFolder = Join-String -Separator '\' -InputObject $_, "app\src\main"
                        Write-Host "`$SourceFolder = $SourceFolder[Copy-DesignatedFolderContents:position seven]"  -ForegroundColor Green

                        if (Test-Path -Path $SourceFolder -PathType 'Container') {
                            $SourceFolderContents = Join-String -Separator '\' -InputObject $SourceFolder, "*"

                            # 将文件夹的所有内容拷贝到目标文件夹下
                            Copy-Item $SourceFolderContents $DesignatedFolder -Recurse -Force
                        } else {
                            Write-Warning "The folder at '$SourceFolder' not exists files or subfolders[Copy-DesignatedFolderContents:position seven]"
                        }
# Copy-DesignatedFolderContents 结束！！！




                    }
                } else {
                    Write-Host "目标父文件夹不存在: $DistinationParentFolder ..." -ForegroundColor Red
                }

# Delete-DesignatedFolderContents 结束！！！




























            } -ThrottleLimit 4

        } catch {
            Write-Error $_.Exception.Message
        }
    }
}

Export-ModuleMember -Function Copy-BatchSourceFolderToDestination
