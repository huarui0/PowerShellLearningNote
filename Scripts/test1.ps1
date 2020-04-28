# test1.ps1
param(
    $x = "",
    $y = ""
)

&echo $x $y


# $TestVarible = Read-Host -Prompt '请：FileName：'
# Write-Host "请：FileName：: `$TestVarible = $TestVarible" -ForegroundColor Green


$FilePath = "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolderTmp\udlocal-Sunshine"
Write-Host "第二层循环_开始1_直接更改 目标文件: `$FilePath = $FilePath" -ForegroundColor Red -BackgroundColor Yellow
$FileName = "src"
Write-Host "第二层循环_开始1_直接更改 目标文件: `$FileName = $FileName" -ForegroundColor Red -BackgroundColor Yellow
Get-ChildItem -File -Path $FilePath -Include *.java, *.xml  -Recurse | Where-Object {$_ -like '*src*'}  |ForEach-Object -Parallel {

    $SelectString = Select-String -Path $_ -Pattern "android.support.v7.app.AppCompatActivity"
    Write-Host "第二层循环_开始1_直接更改 Select `$SelectString = $SelectString" -ForegroundColor Green
    Write-Host "第二层循环_开始1_直接更改 目标文件: `$_ = $_" -ForegroundColor Blue
    if (Select-String -Path $_ -Pattern "android.support.v7.app.AppCompatActivity") {

        Write-Host "第二层循环_开始1_直接更改 Select `$_ = $_" -ForegroundColor Red
    }

} -ThrottleLimit 4