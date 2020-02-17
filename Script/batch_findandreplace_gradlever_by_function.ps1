# https://stackoverflow.com/questions/56621552/passing-powershell-get-childitem-filter-parameter-through-a-string-variable-is-n
# Passing Powershell Get-ChildItem filter parameter through a string variable is not working
Function Create-Filter($filter) {    
    $str = $filter.Split(',') | ForEach-Object {"""*.$($_.Trim())"""}

    $str = $str -join ', '
    [string]$str
    return
}

Function WriteToFileMethodOne($FilePath,$LineToWrite = 'Write this to the text file 1') {
    if (Test-Path $FilePath) {
        Add-Content -Path $FilePath -Value $LineToWrite
    } else {
        return "The file $FilePath does not exist"
    }
}

Function WriteToFileMethodTwo {
    if (Test-Path $args[0]) {
        Add-Content -Path $args[0] -Value $args[1]
    } else {
        return "The file $($args[0]) does not exist"
    }
}

function WriteContentToFileDirectly {
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Leaf'})]
        [string] $FilePath,
        [Parameter(Mandatory = $true)]
        [string] $find,
        [Parameter(Mandatory = $true)]
        [string] $replace
    )
    if (Test-Path $FilePath) {
        # Sample Parameter Value:
        # $FilePath = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt'
        # $find = 'foo'
        # $replace = 'bar'
        $content = Get-Content -Path $FilePath
        $newContent = $content -replace $find, $replace
        $newContent | Set-Content -Path $FilePath

        # Sample Parameter Value:
        # $FilePath = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_sample2.txt'
        # $find = 'foo'
        # $replace = 'bar'
        (Get-Content -Path $FilePath).replace($find, $replace) | Set-Content -Path $FilePath
    } else {
        return "The file $FilePath does not exist"
    }
}

function WriteContentToFileByRegX {
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Leaf'})]
        [string] $FilePath,
        [Parameter(Mandatory = $true)]
        [string] $find,
        [Parameter(Mandatory = $true)]
        [string] $replace
    )
    if (Test-Path $FilePath) {
        # Sample Parameter Value:
        # $FilePath = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt'
        # $find = 'foo'
        # $replace = 'bar'
        $content = Get-Content -Path $FilePath
        $newContent = $content -replace $find, $replace
        $newContent | Set-Content -Path $FilePath

    } else {
        return "The file $FilePath does not exist"
    }
}

function WriteToFileByTempFile {
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Leaf'})]
        [string] $FilePath,
        [Parameter(Mandatory = $true)]
        [string] $find,
        [Parameter(Mandatory = $true)]
        [string] $replace
    )
    
    $tempFilePath = "$env:TEMP\$($FilePath | Split-Path -Leaf)"

    (Get-Content -Path $FilePath) -replace $find, $replace | Add-Content -Path $tempFilePath

    Remove-Item -Path $filePath
    Move-Item -Path $tempFilePath -Destination $filePath
}




function Batch_FindandReplace_IntTextFile {
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
    [CmdletBinding(DefaultParameterSetName = 'NewFile')]
    [OutputType()]
    param (
        [Parameter(Mandatory = $true)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Any'})]
        [string[]]$FilePaths,
        [Parameter()]
        [string]$FilePath,
        [Parameter(Mandatory = $true)]
        [string]$FileName,
        [Parameter(Mandatory = $true)]
        [string]$Find,
        [Parameter()]
        [string]$Replace,
        [Parameter(ParameterSetName = 'NewFile')]
        [ValidateScript({ Test-Path -Path ($_ | Split-Path -Parent) -PathType 'Any' })]
        [string]$OriginalFilePath,

        [Parameter(ParameterSetName = 'NewFile')]
        [switch]$Force,

        [Parameter()]
        [switch]$TempFileMode
    )
    begin {
        $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }
    process {
        try {
            Import-Csv .\TestFolder\Excel_Test.csv
            $CsvObjectArry = Import-Csv .\TestFolder\Excel_Test.csv
            foreach ($object in $CsvObjectArry) {
                Write-Host "the name of the object is: " $object.FilePaths -ForegroundColor $object.ObjectPropertyColor

                Write-Host "the name of the object is: " $object.ObjectPropertyColor -ForegroundColor $object.ObjectPropertyColor
            }
            # $CsvObjectArry

            $startTime = Get-Date
            Write-Host $startTime
            Write-Host "`$Replace = $Replace"

            $FileEntries = $FilePaths | ForEach-Object -Parallel {
                Write-Host  "Output: $_"
                $FilePath = $_
                Write-Host "第一层循环`$FilePath = $FilePath"
                # Start-Sleep 1
                # $FileCount = $FilePaths.Count
                # $FileCount
                try {
                    if ($Replace) {            
                        Write-Host "`$Replace is not EMPTY"
                    } else {            
                        Write-Host "`$Replace is EMPTY or NULL"
                    }

                    Write-Host "`$Replace `-ne `$Find is" ($Find -ne $Replace)
                    Write-Host ([string]::IsNullOrEmpty($Replace))
                    Write-Host ($null -ne $Replace)
                    # if (($Replace -ne $Find) -and ($Replace -ne $null)) {
                    if (-not ($Replace -eq $Find) -and ($Replace)) {
                        Write-Host "开始1"




                        # 批量更新文件内容
                        # Get-ChildItem -Path $FilePath -Recurse | Where-Object{$_.Name.ToLower().Contains("gradle-wrapper.properties")} | ForEach-Object -Parallel {
                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value $using:FileName | ForEach-Object -Parallel {
                            # "$using:Find $_"
                            Write-Host "第一个循环`$FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host $_

                        # if ($FilePath) { 不用判断这句了！！

                            if (Test-Path -Path $using:FilePath -PathType 'Leaf') {
                                # 判断是否是 原始文件拷贝到目标文件方式
                                if ($OriginalFilePath) {
                                    # 用 OriginalFilePath 判断 是否将 OriginalFilePath 中的原文件，拷贝到目标位置
                                    # 如果是强制覆盖
                                    if ($using:Force.IsPresent) {
                                        Remove-Item -Path $using:FilePath -Force
                                        (Get-Content $using:OriginalFilePath) -replace $using:Find, $using:Replace | Add-Content -Path $using:FilePath -Force

                                    } else {
                                        Write-Warning "The file at '$using:FilePath' already exists and the -Force param was not used"
                                    }
                                } else {
                                    # 否则 
                                    # 1. 直接更改 目标文件

                                    Get-Content $using:OriginalFilePath -Find $using:Find -Replace $using:Replace | Add-Content -Path "$using:OriginalFilePath.tmp" -Force
                                    Remove-Item -Path $using:OriginalFilePath
                                    Move-Item -Path "$using:OriginalFilePath.tmp" -Destination $using:FilePath

                                    # 2. 备份原文件，然后更新文件内容
                                }
                            } else { # 应该没有这个选项，目标一定有文件！
                                # 将源文件 拷贝到目标位置（目标位置没有指定文件） - 未测试，有问题？
                                (Get-Content $using:OriginalFilePath) -replace $using:Find, $using:Replace | Add-Content -Path $using:FilePath -Force
                            }

                        } -ThrottleLimit 4

                    } else {
                        # 搜索 文件夹 包含
                        # 搜索 文件 名称包含 文件扩展名
                        # 搜索 文件 内容包含 字符串
                        Write-Host "开始2！"
                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value $using:FileName | ForEach-Object -Parallel {
                            # "$using:FilePath $_"
                            Write-Host "第二个1循环`$FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host $_ -ForegroundColor Green
                        } -ThrottleLimit 4
                        # Select-String，则$FilePath必须为-Leaf，否则无意义
                        if (Test-Path -Path $FilePath -PathType 'Leaf') {
                            Select-String -Path $_ -Pattern "$using:Find"
                        } else {
                            Get-ChildItem -Path $_ -Recurse | Where-Object -Property name -eq -Value $using:FileName | ForEach-Object -Parallel {
                                # "$using:Find $_"
                                Write-Host "第二个2循环`$FilePath = $using:FilePath"
                                Write-Host $_ -ForegroundColor Blue

                            } -ThrottleLimit 4
            
                        }
                    }

                } catch {
                    Write-Error $_.Exception.Message
                }

            }  -ThrottleLimit 4
            $endTime = Get-Date
            $endTime
            $totalTime = $endTime - $startTime
            $newTimeSpan = New-TimeSpan –Start $startTime –End $endTime
            Write-Host "开始时间: $startTime"
            "开始时间:" + $startTime
            Write-Host "结束时间: $endTime"
            "结束时间:" + $endTime
            Write-Host "总执行时间: $totalTime"
            "总执行时间:" + $totalTime
            Write-Host "总用时: $newTimeSpan 秒"
            -join ("总用时: ", $newTimeSpan, " 秒!")
        } catch {
            Write-Error $_.Exception.Message
        }           
    }
}

# 用一个已更新文件覆盖一批文件夹中与原文件内容不一致的相同文件的例子
# Batch_FindandReplace_IntTextFile -OriginalFilePath 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder' -FilePaths 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\AndroidX','E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\Practices' -FileName "gradle-wrapper.properties" -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip' -Force



# 更新一批文件夹中相同文件的例子
# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件夹中相同文件的例子
# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件的例子
# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX\PureX\gradle\wrapper\gradle-wrapper.properties' -Find 'gradle-6.1-rc-1-all.zip'  -Replace 'gradle-6.1-rc-3-all.zip'

# 查找的例子
# $FilePath
# $Find
# Batch_FindandReplace_IntTextFile -FilePath $FilePath -Find $Find
Batch_FindandReplace_IntTextFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\AndroidX','E:\AppPractice' -FileName "gradle-wrapper.properties" -Find 'gradle-6.1-rc-1-all.zip'
