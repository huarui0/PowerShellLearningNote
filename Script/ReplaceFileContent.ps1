Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt' -Value 'foo bar baz'
$content = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt'
$newContent = $content -replace 'foo', 'bar'
$newContent | Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file.txt'