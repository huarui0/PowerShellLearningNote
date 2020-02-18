

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


            $FileEntries = $FilePaths | ForEach-Object -Parallel {



                    # if (($Replace -ne $Find) -and ($Replace -ne $null)) {
                    if (-not ($Replace -eq $Find) -and ($Replace)) {
                        Write-Host "开始1"


                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value $using:FileName | ForEach-Object -Parallel {
                            # "$using:Find $_"
                            Write-Host "第一个循环`$FilePath = $using:FilePath" # 注意：using: 的用法，表示是上一级的变量
                            Write-Host $_



                        # if ($FilePath) { 不用判断这句了！！

                            if (Test-Path -Path $using:FilePath -PathType 'Leaf') {
                                # 判断是否是 原始文件拷贝到目标文件方式
                                if ($OriginalFilePath) {

                                } else {

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

                    }


            }  -ThrottleLimit 4
        
    }
}
