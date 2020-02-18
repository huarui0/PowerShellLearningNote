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




function Batch_FindandReplace_InTextFile {
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
        [ValidateScript({ Test-Path -Path ($_ | Split-Path -Parent) -PathType 'Any' })]
        [string]$SourceFilePath,

        [Parameter()]
        [string]$TempSourceFilePath,

        [Parameter(ParameterSetName = 'NewFile')]
        [ValidateScript({ Test-Path -Path ($_ | Split-Path -Parent) -PathType 'Any' })]
        [string]$DestinationFilePath,

        [Parameter()]
        [string]$SourceFileName,

        [Parameter(ParameterSetName = 'NewFile')]
        [switch]$Force = $false,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceOverride,
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

            [String]$TempSourceFilePath = Join-String -Separator '\' -InputObject $OriginalFilePath,$SourceFileName
            Write-Host "起始位置: `$TempSourceFilePath = $TempSourceFilePath"
# Break
            ## 特别注意：如果是从源文件拷贝到多个目标文件夹的模式，则需要先处理原文件，待全部处理完毕，则需要将临时文件删除。切记！
            ## --- delete temp file see below-------
            if ($OriginalFilePath) {
                if ($TempFileMode) {
                    Write-Host ("特别注意：如果是从源文件拷贝到多个目标文件夹的模式，则需要先处理原文件，待全部处理完毕，则需要将临时文件删除。切记！") -ForegroundColor Red
                    (Get-Content $TempSourceFilePath) -Replace $Find, $Replace | Add-Content -Path "$TempSourceFilePath.tmp" -Force
                } else {
                    # 直接更改源文件
                    (Get-Content $TempSourceFilePath) -Replace $Find, $Replace | Add-Content -Path $TempSourceFilePath -Force  
                }

            }
            ## --------------------------------

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
                    Write-Host "第一层循环`$Replace = $Replace"
                    Write-Host "第一层循环`$Find = $Find"
                    Write-Host "第一层循环`$using:Replace = $using:Replace"
                    Write-Host "第一层循环`$using:Find = $using:Find"

                    Write-Host "`$Replace `-ne `$Find is" ($Replace -ne $Find)
                    Write-Host "`$using:Replace `-ne `$using:Find is" ($using:Replace -ne $using:Find)
                    Write-Host "IsNullOrEmpty(`$Replace) =" ([string]::IsNullOrEmpty($Replace))
                    Write-Host "IsNullOrEmpty(`$using:Replace) =" ([string]::IsNullOrEmpty($using:Replace))
                    Write-Host "(`$null -ne `$using:Replace) =" ($null -ne $using:Replace)

                    Write-Host ("第一个循环`$OriginalFilePath = $OriginalFilePath")
                    Write-Host ("第一个循环`$using:OriginalFilePath = $using:OriginalFilePath")


                    if($PSBoundParameters.ContainsKey('ForceMode')) {
                        # switch parameter was explicitly passed by the caller
                        # grab its value
                        $requestparams.Code = $ForceMode.IsPresent
                    }
                    else {
                        # parameter was absent from the invocation, don't add it to the request 
                        Write-Host "`$ForceMode is not Present"
                    }


                    Write-Host ("第一个循环`$Force = $Force")

                    Write-Host ("第一个循环`$using:Force = $using:Force")

                    Write-Host "第一层循环`$Force.IsPresent =" ($Force.IsPresent)

                    # Write-Host "第一层循环`$using:Force.IsPresent =" ($using:Force.IsPresent)

                    $OriginalFilePath = $using:OriginalFilePath
                    Write-Host ("第一个循环_赋值后：`$OriginalFilePath = $OriginalFilePath")

                    $SourceFileName = $using:SourceFileName
                    Write-Host ("第一个循环_赋值后：`$SourceFileName = $SourceFileName")

                    $Force = $using:Force
                    Write-Host ("第一个循环_赋值后：`$Force = $Force")

                    $TempFileMode = $using:TempFileMode
                    Write-Host ("第一个循环_赋值后：`$TempFileMode = $TempFileMode")

                    # if (($Replace -ne $Find) -and ($Replace -ne $null)) {
                    if (-not ($using:Replace -eq $using:Find) -and ($using:Replace)) {
                        Write-Host "开始1"

                        $Find = $using:Find
                        Write-Host ("第一个循环_赋值后：`$Find = $Find")
                        $Replace = $using:Replace
                        Write-Host ("第一个循环_赋值后：`$Replace = $Replace")

                        $FileName = $using:FileName
                        Write-Host ("第一个循环_赋值后：`$FileName = $FileName")
# Break    
                        # 批量更新文件内容
                        # Get-ChildItem -Path $FilePath -Recurse | Where-Object{$_.Name.ToLower().Contains("gradle-wrapper.properties")} | ForEach-Object -Parallel {
                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value $using:FileName | ForEach-Object -Parallel {
                            # "$using:Find $_"
                            Write-Host "第二层循环_开始1: `$using:FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host "第二层循环_开始1: `$_ = $_"

                            Write-Host "第二层循环_开始1: `$using:FileName = $using:FileName"

                            $ForceOverride = $using:Force

                            Write-Host "第二层循环_开始1: `$ForceOverride = $ForceOverride"

                            # $SourceFilePath = ($using:OriginalFilePath,$using:FileName) | Join-String -Separator '\'

                            $SourceFilePath = $using:OriginalFilePath,$using:SourceFileName
                            [string]$SourceFilePath = Join-String -Separator '\' -InputObject $SourceFilePath
                            Write-Host "第二层循环_开始1: `$SourceFilePath = $SourceFilePath"
# bug 如果是文件，而不是文件夹，就会出错！！！！！！！！！！！
                            $DestinationFilePath = $_, $using:SourceFileName
                            [string]$DestinationFilePath = Join-String -Separator '\' -InputObject $DestinationFilePath
                            Write-Host "第二层循环_开始1: `$DestinationFilePath = $DestinationFilePath"


                        # if ($FilePath) { 不用判断这句了！！


                            # 在本session中重新赋值为上个session的值，特别重要. 但是 重新赋值的无法传递，名称冲突的缘故？
                            # $Force = $using:Force
                            # Write-Host "第二层循环_开始1:`$Force = " $Force.IsPresent

                            # $using:ForceMode 无法传递，暂时添加 $ForceDelete
                            # 特别特别重要：必须在 param () 中预先定义（变量的Scope概念）
                            # [Switch]$ForceOverride = ($using:Force.IsPresent)

                            Write-Host "第二层循环_开始1_进入更改: `$TempFileMode = $TempFileMode"
                            Write-Host "第二层循环_开始1_进入更改: `$using:TempFileMode = $using:TempFileMode"


                            if (Test-Path -Path $_ -PathType 'Leaf') {
                                # 判断是否是 原始文件拷贝到目标文件方式
                                Write-Host "第二层循环_开始1_进入更改后: `$using:OriginalFilePath = $using:OriginalFilePath"
                                Write-Host "第二层循环_开始1_进入更改后Leaf: `$using:TempFileMode = $using:TempFileMode"

                                if ($using:OriginalFilePath) {
                                    # 用 OriginalFilePath 判断 是否将 OriginalFilePath 中的原文件，拷贝到目标位置
                                    # 如果是强制覆盖

                                    if ($ForceOverride) {
                                        if ($using:TempFileMode) {
                                            # --- 要移到前面去，不能放这里！--- #
                                            # Get-Content $SourceFilePath -Find $using:Find -Replace $using:Replace | Add-Content -Path "$SourceFilePath.tmp" -ForceOverride
                                            # ------------------------------ #

                                            Remove-Item -Path $_   # -Force
                                            # 考虑到其他文件还要用，不能用Move-Item，而要用Copy-Item的方式，待全部处理完后，统一删除临时的文件。
                                            # Move-Item -Path "$using:OriginalFilePath.tmp" -Destination $using:FilePath

                                            # 这句只能Copy为 .tmp
                                            # Copy-Item -Path "$SourceFilePath.tmp" -Destination $_ # 因未用变量赋值$_,所以可以直接用$_, 不能用 $using:FilePath！

                                            # 改名需要用下面这句
                                            Copy-Item -Path "$SourceFilePath.tmp" -Destination $DestinationFilePath







                                        } else {
                                            Write-Host "第二层循环_开始1: `$using:TempFileMode = $using:TempFileMode"
                                        }
                                        ## ----- 不用了 ----- ##
                                        # Remove-Item -Path $using:FilePath -Force
                                        # (Get-Content $SourceFilePath) -replace $using:Find, $using:Replace | Add-Content -Path $using:FilePath -Force

                                    } else {
                                        Write-Warning "The file at '$_' already exists and the -Force param was not used"
                                    }
                                } else {
                                    # 否则 
                                    # 1. 直接更改 目标文件

                                    # 2. 备份原文件，然后更新文件内容
                                }

                            } elseif (Test-Path -Path $_ -PathType 'Container') {
                                # 如果目标文件夹存在，但没有文件了，就直接将原文件拷贝到目标文件夹。
                                Write-Host "第二层循环_直接拷贝到目标文件夹: `$using:FilePath = $using:FilePath"

                                Write-Host "第二层循环_直接拷贝到目标文件夹: `$_ = $_"

                                Write-Host "第二层循环_直接拷贝到目标文件夹: `$SourceFilePath = $SourceFilePath"

                                Write-Host "第二层循环_直接拷贝到目标文件夹: `$SourceFilePath.tmp = $SourceFilePath.tmp"

                                Write-Host "第二层循环_开始1_进入更改后Container: `$DestinationFilePath = $DestinationFilePath" -ForegroundColor Green

                                Write-Host "第二层循环_开始1_进入更改后Container: `$using:TempFileMode = $using:TempFileMode" -ForegroundColor Green
                               
                                Write-Host "第二层循环_开始1_进入更改后Container: `$ForceOverride = $ForceOverride" -ForegroundColor Green

                                # 所以要在这里判断目标文件夹是否有文件，如果没有文件，则将源文件拷贝到目标文件夹，这个方法不好，让逻辑复杂了。
                                if (Test-Path -Path $DestinationFilePath -PathType 'Leaf') {
                                    Write-Host "第二层循环_开始1_进入更改后Container: 进入$DestinationFilePath..." -ForegroundColor Red
                                    if ($ForceOverride) {
                                        Write-Host "第二层循环_开始1_进入更改后Container: 开始删除..." -ForegroundColor Red

                                        Remove-Item -Path $DestinationFilePath -Force
                                    }
                                } else {

                                }

                                if ($using:OriginalFilePath) {
                                    # 用 OriginalFilePath 判断 是否将 OriginalFilePath 中的原文件，拷贝到目标位置
                                    # 如果是强制覆盖
                                    Write-Host "第二层循环_开始1_进入更改后Container: 进入`$using:OriginalFilePath..." -ForegroundColor Red

                                    if ($ForceOverride) {
                                        Write-Host "第二层循环_开始1_进入更改后Container: 进入`$ForceOverride..." -ForegroundColor Yellow

                                        if ($using:TempFileMode) {

                                            Write-Host "第二层循环_开始1_进入更改后,开始更新前Container: 进入`$ForceOverride..." -ForegroundColor Yellow

                                            # 考虑到其他文件还要用，不能用Move-Item，而要用Copy-Item的方式，待全部处理完后，统一删除临时的文件。
                                            # Move-Item -Path "$SourceFilePath.tmp" -Destination $using:FilePath
                                            # 这句只能Copy为 .tmp
                                            # Copy-Item -Path "$SourceFilePath.tmp" -Destination $_ # 因未用变量赋值$_,所以可以直接用$_, 不能用 $using:FilePath！

                                            # 改名需要用下面这句
                                            Copy-Item -Path "$SourceFilePath.tmp" -Destination $DestinationFilePath
                                            # Remove-Item -Path "$DestinationFilePath.tmp" -Force
                                        } else {
                                            Write-Host "第二层循环_开始1: `$using:TempFileMode = $using:TempFileMode"
                                        }
                                    } else {
                                        Write-Warning "The file at '$DestinationFilePath' already exists and the -Force param was not used"
                                    }
                                } else {
                                    # 否则 
                                    # 1. 直接更改 目标文件

                                    # 2. 备份原文件，然后更新文件内容
                                }


                            } else { 
                                # 如果是文件夹覆盖（替换），则在这个选项

                                # 将源文件夹 拷贝到目标位置（目标位置没有指定文件夹） - 未测试，有问题？
                                (Get-Content $using:OriginalFilePath) -replace $using:Find, $using:Replace | Add-Content -Path $using:FilePath -ForceOverride
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

            # 如果是源文件同时是临时文件模式，将源文件的临时文件删除
            ## --- delete temp file is here-------
            if ($OriginalFilePath) {
                if ($TempFileMode) {
                    if (Test-Path -Path "$TempSourceFilePath.tmp" -PathType 'Leaf') {
                        Remove-Item -Path "$TempSourceFilePath.tmp" -Force
                    }
                } else {

                }

            }
            ## --------------------------------


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

# 直接用更新的源文件拷贝到一批指定的文件夹的例子（如果文件夹中有文件，则直接覆盖）
# 事实上，OriginalFilePath （定义为 Container） 就是 SourceFilePath（定义为Leaf）
# FileName 既可以是文件夹，也可以是文件，如果是文件夹，就搜索包含该名称的文件夹，如果是文件，就搜索包含该名称的文件。
# Batch_FindandReplace_InTextFile -OriginalFilePath 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder' -FilePaths 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\AndroidX','E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\Practices' -FileName "wrapper" -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-7-all.zip' -Force:$true -TempFileMode:$true -SourceFileName "gradle-wrapper.properties"


# 正式
Batch_FindandReplace_InTextFile -OriginalFilePath 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder' -FilePaths 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\AndroidX','E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\Practices' -FileName "wrapper" -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-7-all.zip' -Force:$true -TempFileMode:$true -SourceFileName "gradle-wrapper.properties"



# 用一个已更新文件覆盖一批文件夹中与原文件内容不一致的相同文件的例子
# Batch_FindandReplace_InTextFile -OriginalFilePath 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder' -FilePaths 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\AndroidX','E:\Notes\4_LearningNotes\PowerShellLearningNote\Script\TestFolder\Practices' -FileName "gradle-wrapper.properties" -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip' -Force:$true -TempFileMode:$true

# 更新一批文件夹中相同文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件夹中相同文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX\PureX\gradle\wrapper\gradle-wrapper.properties' -Find 'gradle-6.1-rc-1-all.zip'  -Replace 'gradle-6.1-rc-3-all.zip'

# 查找文件的例子
# $FilePath
# $Find
# Batch_FindandReplace_InTextFile -FilePath $FilePath -Find $Find
# Batch_FindandReplace_InTextFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\AndroidX','E:\AppPractice' -FileName "gradle-wrapper.properties" -Find 'gradle-6.1-rc-1-all.zip'

# 查找文件夹的例子
# $FilePath
# $Find
# Batch_FindandReplace_InTextFile -FilePath $FilePath -Find $Find
# Batch_FindandReplace_InTextFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\AndroidX','E:\AppPractice' -FileName "wrapper" -Find 'gradle-6.1-rc-1-all.zip'
