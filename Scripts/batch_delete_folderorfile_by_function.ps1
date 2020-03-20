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
        [Parameter()]
        [string] $actionMode   # delete or Delete ,search or Search.
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




function Batch_Delete_FolderOrFile {
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

        [Parameter(Mandatory = $false)]
        [ValidateScript({Test-Path -Path $_ -PathType 'Any'})]
        [string[]]$ExcludeFilePaths,
        
        [Parameter()]
        [string]$FilePath,
        [Parameter(Mandatory = $true)]
        [string]$Find,
        [Parameter()]
        [string]$Replace,

        [Parameter()]
        [string] $actionMode,   # delete or Delete ,search or Search.



        [Parameter(ParameterSetName = 'NewFile')]
        [ValidateScript({ Test-Path -Path ($_ | Split-Path -Parent) -PathType 'Any' })]
        [string]$OriginalFilePath,

        [Parameter(ParameterSetName = 'NewFile')]
        [switch]$Force,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceMode = $false,
        [Parameter(Mandatory=$false)]
        [Switch]
        $ForceDelete
    )
    begin {
        $Find = [regex]::Escape($Find)
        # 注：只是 $Find 用正则表达式，$Replace 一定是正确的数据，所以不可能是正则表达表达式
        # $Replace = [regex]::Escape($Replace)
    }
    process {
        try {
            Write-Host $PSBoundParameters

            Import-Csv .\TestFolder\Excel_Test.csv
            $CsvObjectArry = Import-Csv .\TestFolder\Excel_Test.csv
            foreach($object in $CsvObjectArry) {
                Write-Host "the name of the object is: " $object.FilePaths -ForegroundColor $object.ObjectPropertyColor

                Write-Host "the name of the object is: " $object.ObjectPropertyColor -ForegroundColor $object.ObjectPropertyColor
            }
            # $CsvObjectArry

            $startTime = Get-Date
            $startTime
            $Replace

            $FileEntries = $FilePaths | ForEach-Object -Parallel {
                "Output: $_"
                $FilePath = $_

                Write-Host "第一个循环`$FilePath = $FilePath"
                # Start-Sleep 1
                # $FileCount = $FilePaths.Count
                # $FileCount
                try {
                    if ($using:actionMode) {            
                        Write-Host "`$actionMode is not EMPTY"
                    } else {            
                        Write-Host "`$actionMode is EMPTY or NULL"
                    }



                    if($PSBoundParameters.ContainsKey('ForceMode')) {
                        # switch parameter was explicitly passed by the caller
                        # grab its value
                        $requestparams.Code = $ForceMode.IsPresent
                    }
                    else {
                        # parameter was absent from the invocation, don't add it to the request 
                        Write-Host "`$ForceMode is not Present"
                    }


                    Write-Host ("第一个循环`$ForceMode = $ForceMode")

                    Write-Host ("第一个循环`$using:ForceMode = $using:ForceMode")

                    Write-Host "第一层循环`$ForceMode.IsPresent =" ($ForceMode.IsPresent)

                    Write-Host "第一层循环`$using:ForceMode.IsPresent =" ($using:ForceMode.IsPresent)

                    Write-Host ([string]::IsNullOrEmpty($actionMode))
                    Write-Host ([string]::IsNullOrEmpty($using:actionMode))

                    Write-Host (($using:actionMode).ToLower().Contains('delete'))

                    # if (($Replace -ne $Find) -and ($Replace -ne $null)) {
                    if (($using:actionMode).ToLower().Contains('delete')) {
                        Write-Host "开始1"


                        # 在本session中重新赋值为上个session的值，特别重要. 但是 重新赋值的无法传递，名称冲突的缘故？
                        $ForceMode = $using:ForceMode

                        # $using:ForceMode 无法传递，暂时添加 $ForceDelete
                        # 特别特别重要：必须在 param () 中预先定义（变量的Scope概念）
                        [Switch]$ForceDelete = ($using:ForceMode.IsPresent)
                        Write-Host "第一个`$ForceDelete = " $ForceDelete.IsPresent

                        Get-ChildItem -Path $FilePath -Attributes Directory -Recurse | Where-Object -Property name -eq -Value $using:Find | ForEach-Object -Parallel {
                            # "$using:Find $_"
                            Write-Host "第一个循环`$FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host "`$_ = $_"

                            Write-Host "第二个`$ForceDelete = " $using:ForceDelete.IsPresent
                            Write-Host "第二个`$ForceMode = " $using:ForceMode.IsPresent
                            


                            if ((Test-Path -Path $_ -PathType 'Container')) {
                                # 用 OriginalFilePath 判断 是否将 OriginalFilePath 中的原文件，拷贝到目标位置
                                # 如果是强制覆盖
                                if ($using:ForceDelete.IsPresent) {
                                    Remove-Item -Path $_ –recurse -Force
                                    Write-Host "文件夹：$_ 已删除" -ForegroundColor Green
                                } else {
                                    Write-Warning "The file at '$_' already exists and the -ForceMode param was not used"
                                    Write-Host "文件夹：$_ 已存在" -ForegroundColor Green
                                }
                            } else {
                                # 文件夹不存在！
                                Write-Warning "The folder '$using:FilePath' not exists"
                            }

                        } -ThrottleLimit 4

                    } else { # 查找文件夹功能
                        # 搜索 文件夹 包含
                        # 搜索 文件 名称包含 文件扩展名
                        # 搜索 文件 内容包含 字符串
                        Write-Host "开始2！"
                        Get-ChildItem -Path $FilePath  -Attributes Directory -Recurse | Where-Object -Property name -eq -Value $using:Find | ForEach-Object -Parallel {
                            # "$using:FilePath $_"
                            Write-Host "第二个1循环`$FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host $_
                        } -ThrottleLimit 4

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

# 更新一批文件夹中相同文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件夹中相同文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# 仅更新一个文件的例子
# Batch_FindandReplace_InTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX\PureX\gradle\wrapper\gradle-wrapper.properties' -Find 'gradle-6.1-rc-1-all.zip'  -Replace 'gradle-6.1-rc-3-all.zip'

# 查找的例子
# $FilePath
# $Find
# Batch_FindandReplace_InTextFile -FilePath $FilePath -Find $Find
# Batch_FindandReplace_InTextFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\AndroidX','E:\AppPractice' -FileName "gradle-wrapper.properties" -Find 'gradle-6.1-rc-1-all.zip'

# 查找文件夹的例子
# $FilePath
# $Find
# Batch_FindandReplace_InTextFile -FilePath $FilePath -Find $Find

# Batch_Delete_FolderOrFile -FilePaths 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\AndroidX','E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\Practices'  -Find "build" -actionMode 'delete'  -ForceMode:$false


Batch_Delete_FolderOrFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\Examples\Android4.0'  -Find "architecture-components-samples-master" -actionMode 'delete'  -ForceMode:$true




# Batch_Delete_FolderOrFile -FilePaths 'E:\AndroidDev', 'E:\AndroidStudy', 'E:\AppDemo2016', 'E:\AppDev', 'E:\AppDev2016', 'E:\AppDev2017', 'E:\AppDev2017_Gradle', 'E:\AppDevKotlin', 'E:\AppDev_4.5', 'E:\AppDev_JavaEE', 'E:\AppDev_Mobile', 'E:\AppDev_Reserved', 'E:\AppPractice', 'E:\AppRmi', 'E:\AppTest', 'E:\CLangDev', 'E:\Cpractice', 'E:\Cpractice2016', 'E:\DataDev', 'E:\DemoDev', 'E:\JavaDev', 'E:\JavaDev_bak_20171014', 'E:\JavaDev_bak_20190510', 'E:\JavaEEDev', 'E:\JavaTest', 'E:\NodeJsDev', 'E:\PHPDev', 'E:\PythonDev', 'E:\Resources', 'E:\WebDev2017' -Find "target" -actionMode 'delete'  -ForceMode:$true  # -ExcludeFilePaths "D:\Program Files","D:\ProgramData"