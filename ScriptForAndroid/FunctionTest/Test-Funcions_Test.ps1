Import-Module -Name E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\FunctionTest\Test-Functions.psm1 -Verbose

Test2 -a Power -b Shell


# 非特殊情况：-ForceOverride:$false 要设置为 $false
# ForceOverride 就是 文件夹存在时，是否覆盖。true：覆盖，false：不覆盖
# 非特殊情况：-ForceDelete:$false 要设置为 $false

Copy-BatchSourceFolderToDestination -TemplateFolder "E:\AndroidDev\AndroidStudioProjects\Studies_Template\SunShineX" -SourceParentFolder "E:\AndroidDev\AndroidStudioProjects\Studies&Practices\01_courses_Code\ud851-Sunshine" -DistinationParentFolder "E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\udlocal-Sunshine" -ForceOverride:$false -isCopyFolderContents:$true -isDeleteFolderContents:$true -ForceDelete:$false