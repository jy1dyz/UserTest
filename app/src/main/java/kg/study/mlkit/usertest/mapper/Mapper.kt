package kg.study.mlkit.usertest.mapper


interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
}

class PersonMapper(private val salaryMapper: Mapper<SalarySrc, SalaryDst>) : Mapper<PersonSrc, PersonDst> {
    override fun transform(data: PersonSrc): PersonDst = PersonDst(
        data.name, salary = salaryMapper.transform(data.salary)
    )
}

class SalaryMapper: Mapper<SalarySrc, SalaryDst> {
    override fun transform(data: SalarySrc): SalaryDst =
        SalaryDst(amount = data.amount)
}