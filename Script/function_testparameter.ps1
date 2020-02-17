Function WriteToFileMethodOne($FilePath,$LineToWrite = 'Write this to the text file 1') {
    if (Test-Path $FilePath) {
        Add-Content -Path $FilePath -Value $LineToWrite
    } else {
        return "The file $FilePath does not exist"
    }
}

Function WriteToFileMethodTwo {
    if (Test-Path $args[0]) {
        Add-Content -Path $args[0] -Value $args[1]
    } else {
        return "The file $($args[0]) does not exist"
    }
}

# Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_function_testparameter1.txt' -Value 'foo bar baz 1'
# Set-Content -Path 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_function_testparameter2.txt' -Value 'foo bar baz 2'

WriteToFileMethodOne 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_function_testparameter1.txt'
WriteToFileMethodTwo 'E:\Notes\4_LearningNotes\PowerShell\Script\TestFolder\file_function_testparameter2.txt' 'Write this to the text file 2'
