package kg.study.mlkit.usertest.mapper

class PersonSrc(
    val name: String,
    val salary: SalarySrc
)

class PersonDst(
    val name: String,
    val salary: SalaryDst
)

class SalarySrc(
    val amount: Int
)

class SalaryDst(
    val amount: Int
)