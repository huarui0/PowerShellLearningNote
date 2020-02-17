function Batch_FindandReplace_IntTextFile {
    <#
    .SYNOPSIS
        实现在指定的文件或批量文件（名称相同）中，查找和替换文本的功能。
    .PARAMETER FilePaths
        The file path of the text file you'd like to perform a find/replace on.
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
        [string[]]$FilePaths,
        [Parameter()]
        [string]$FilePath,
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
        # $Replace = [regex]::Escape($Replace)
    }
    process {
        try {
            $startTime = Get-Date
            $startTime
            $Replace

            $FileEntries = $FilePaths | ForEach-Object -Parallel {
                "Output: $_"
                $FilePath = $_

                Write-Host "`$FilePath = $FilePath"
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
                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value "gradle-wrapper.properties" | ForEach-Object -Parallel {
                            # "$using:Find $_"
                        } -ThrottleLimit 4

                        # 批量备份原文件，然后更新文件内容
                        # Get-ChildItem -Path $FilePath -Recurse | Where-Object{$_.Name.ToLower().Contains("gradle-wrapper.properties")} | ForEach-Object -Parallel {
                        Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value "gradle-wrapper.properties" | ForEach-Object -Parallel {
                            # "$using:Find $_"
                        } -ThrottleLimit 4
                    } else {
                            Write-Host "开始2！"
                            Get-ChildItem -Path "$using:FilePath" -Recurse | Where-Object -Property name -eq -Value "gradle-wrapper.properties" | ForEach-Object -Parallel {
                                # "$using:Find $_"
                            } -ThrottleLimit 4
                            # Select-String，则$FilePath必须为-Leaf，否则无意义
                            if (Test-Path -Path $FilePath -PathType 'Leaf') {
                                Select-String -Path $FilePath -Pattern $Find
                            } else {
                                Get-ChildItem -Path $FilePath -Recurse | Where-Object -Property name -eq -Value "gradle-wrapper.properties" | ForEach-Object -Parallel {
                                    # "$using:Find $_"
                                } -ThrottleLimit 4
                
                            }
                        }
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

            } -ThrottleLimit 4

        } catch {
            Write-Error $_.Exception.Message
        }           
    }
}

# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip' -Replace 'gradle-6.1-rc-3-all.zip'

# Batch_FindandReplace_IntTextFile -FilePath 'E:\AndroidDev\AndroidStudioProjects\AndroidX\PureX\gradle\wrapper\gradle-wrapper.properties' -Find 'gradle-6.1-rc-1-all.zip'  -Replace 'gradle-6.1-rc-3-all.zip'

Batch_FindandReplace_IntTextFile -FilePaths 'E:\AndroidDev\AndroidStudioProjects\AndroidX' -Find 'gradle-6.1-rc-1-all.zip'

# $FilePath
# $Find
# Batch_FindandReplace_IntTextFile -FilePath $FilePath -Find $Find
