$ips = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShellLearningNote\Scripts\TestFolder\ip.txt'
$ips -replace "\.\d{2}\.", ".90."