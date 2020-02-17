function HelloWorld {
    Write-Host 'Hello world!'
}

function Get-Square([int]$x) {
    $res = $x * $x
    return $res
}


# cls
HelloWorld

$x = Read-Host 'Enter a value'
$sqres = Get-Square $x
Write-Output "$x * $x = $sqres"