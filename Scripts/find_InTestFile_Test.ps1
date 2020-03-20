Function Create-Filter($filter)
{    
    $filter.Split(',') | ForEach-Object {"*.$($_.Trim())"}
    return
}

create-filter ("txt","csv")




Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX

Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX -Name

Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX\*.txt -Recurse -Force


# Get-ChildItem -Path Cert:\* -Recurse -CodeSigningCert



1..100 | ForEach-Object { Add-Content -Path .\LineNumbers.txt -Value "This is line $_." }
Get-Content -Path .\LineNumbers.txt


Get-Content -Path E:\AndroidDev\AndroidStudioProjects\AndroidX\* -Filter *.property -Recurse

//============================================================
(Get-Content -Path .\Notice.txt) |
    ForEach-Object {$_ -Replace 'Warning', 'Caution'} |
        Set-Content -Path .\Notice.txt
Get-Content -Path .\Notice.txt

$cow = $cow.ToUpper 
$cow = $cow.ToLower
$cow.Contains("was a girl")

Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX -Recurse | Where-Object{$_.Name.ToLower().Contains("gradle-wrapper.properties")}
//---------------------------------
-ErrorAction SilentlyContinue
//---------------------------------
Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX -Recurse | Where-Object{$_.Name.Contains("gradle-wrapper.properties")}

Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\Examples -Recurse | Where-Object{$_.Name.Contains("gradle-wrapper.properties")} > gradle-wrapper_file.txt

Get-ChildItem -Path E:\AndroidDev\AndroidStudioProjects\AndroidX -Recurse | Where-Object{$_.Name.Contains("gradle-wrapper.properties")} | ForEach-Object{Rename-Item $_ -NewName $_.name.Replace("&", "AND")}

��ǰһ�ֽ��һ�£����ַ������µķ�ʽ��ǰһ���� string way
Where-Object -Property name -Like -Value "*&*"
Where-Object -Property name -eq -Value "&"

Test-Path $Location -PathType container






$cow = "Wanglai was here!"
$cow.Substring(3)
$cow.Substring(3, 6)
$cow.TrimStart("lai")

$url = "http://www.BoldZebras.com:443"
$newurl = $url.TrimStart("http://")
$hostname = $newurl.Substring(0, $newurl.IndexOf(":"))
