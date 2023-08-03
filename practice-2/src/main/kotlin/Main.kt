open class Person(val name: String, val surname: String, val age: Int)

class Employee(name: String, surname: String, age: Int, val position: String) : Person(name, surname, age)
fun main() {

    val employees = mutableListOf<Employee>()
    while (true) {
        try {
            print("Введите имя. ")
            print("Ввод q выведет список всех людей: ")
            val name: String = readlnOrNull() ?: throw IllegalArgumentException("incorrect name")
            if (name == "q")
                break
            print("Введите фамилию: ")
            val surname: String = readlnOrNull() ?: throw IllegalArgumentException("incorrect surname")
            print("Введите возраст: ")
            val age: Int = readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("age is not int")

            print("Введите должность: ")
            val position: String = readlnOrNull() ?: throw IllegalArgumentException("incorrect position")
            employees.add(Employee(name, surname, age, position))
        }
        catch (e : IllegalArgumentException) {
            println("$e")
        }
    }

    employees.sortBy{it.age}
    println("Список работников по возрастанию возраста: ")
    for (employee in employees) {
        println("${employee.name} ${employee.surname}, ${employee.age}; ${employee.position}")
    }

    val persons = mutableListOf<Person?>()
    // A little bit of fun with upcasting
    employees.forEach{ persons.add(it as? Person) }
    persons.forEach{ println("${it?.name} ${it?.surname}, ${it?.age}")}
}