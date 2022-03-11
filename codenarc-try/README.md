# codenarc-try

Reproducer for https://github.com/CodeNarc/CodeNarc/issues/676.

The code in `com.example.codenarc.Library` will fail CodeNarc's `BracesForTryCatchFinally` rule with

```
CodeNarc Report - 11.03.2022, 09:17:47

Summary: TotalFiles=1 FilesWithViolations=1 P1=0 P2=1 P3=0

File: com/example/codenarc/Library.groovy
    Violation: Rule=BracesForTryCatchFinally P=2 Line=13 Msg=[Opening brace should be on the same line as 'try'] Src=[try (Sql sql = new Sql(null)) {]

[CodeNarc (https://www.codenarc.org) v2.2.0]
```

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
