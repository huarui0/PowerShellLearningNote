Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\file.txt' -Value 'foo bar baz'
$content = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\file.txt'
$newContent = $content -replace 'foo', 'bar'
$newContent | Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\file.txt'


$content = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\file.properties'
$newContent = $content -replace "gradle-[0-9]\.[0-9]-rc-[0-9]-(all|bin)\.zip", 'gradle-6.4-rc-9-all.zip
'
$newContent | Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\file.properties'