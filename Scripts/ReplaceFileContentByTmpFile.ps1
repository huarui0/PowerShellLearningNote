# Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_by_tmp.txt' -Value 'foo bar baz'

$filePath = 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_by_tmp.txt'
$tempFilePath = "$env:TEMP\$($filePath | Split-Path -Leaf)"
$find = 'foo'
$replace = 'bar tmp'

(Get-Content -Path $filePath) -replace $find, $replace | Add-Content -Path $tempFilePath

Remove-Item -Path $filePath
Move-Item -Path $tempFilePath -Destination $filePath