function Batch_FindandReplace_IntTextFile {
    <#
    .SYNOPSIS
        实现在指定的文件或批量文件（名称相同）中，查找和替换文本的功能。
    .PARAMETER FilePath
        The file path of the text file you'd like to perform a find/replace on.
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
        [string[]]$FilePath,
        [Parameter(Mandatory = $true)]
        [string]$Find,
        [Parameter()]
        [string]$Replace,
        [Parameter(ParameterSetName = 'NewFile')]
        [ValidateScript({ Test-Path -Path ($_ | Split-Path -Parent) -PathType 'Container' })]
        [string]$NewFilePath,
        [Parameter(ParameterSetName = 'NewFile')]
        [switch]$Force
    )
    begin {
        $Find = [regex]::Escape($Find)
    }
    process {
        try {
            $FilePath
            # $Find
            # $Replace
            Get-ChildItem -Path $FilePath -Recurse | Where-Object{$_.Name.ToLower().Contains("gradle-wrapper.properties")} | ForEach-Object -Parallel {

                $CurFile = $_.Name
                $CurFile
                if (($Replace -ne $Find) -and ($Replace -ne $null)) {
                    $Replace + ":" + $Find

                } else {
                    $FilePath + ":" + $Replace + "Replace 没有设置或会等于 Find"
                }
            } -ThrottleLimit 4
        } catch {
            Write-Error $_.Exception.Message
        }
    }
}

Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX\PureX\gradle\wrapper\gradle-wrapper.properties' -Find 'gradle-6.1-rc-1-all.zip'  -Replace 'gradle-6.1-rc-3-all.zip'