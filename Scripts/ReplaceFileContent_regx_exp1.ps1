$ips = Get-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\ip.txt'
$ips -replace "\.\d{2}\.", ".90."