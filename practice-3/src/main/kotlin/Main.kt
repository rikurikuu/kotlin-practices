interface Person {
    val name: String
    val surname: String
    val age: Int
}

data class Employee(override val name: String, override val surname: String, override val age: Int, val position: String) : Person
data class StakeHolder(override val name: String, override val surname: String, override val age: Int, val stake: Float) : Person
fun main() {

    val people = mutableListOf<Person>()
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

            println("Выберите, кого вы хотите добавить в массив - работника или акционера.")
            println("1 - добавить работника")
            println("2 - добавить акционера")
            val choice: Int = readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("choice is not int")
            when (choice) {
                1 -> {
                    print("Введите должность: ")
                    val position: String = readlnOrNull() ?: throw IllegalArgumentException("incorrect position")
                    people.add(Employee(name, surname, age, position))
                }
                2 -> {
                    print("Введите долю акций. Только число, без процентов: ")
                    val stake: Float = readlnOrNull()?.toFloatOrNull() ?: throw IllegalArgumentException("stake is not float")
                    people.add(StakeHolder(name, surname, age, stake))
                }
            }

        }
        catch (e : IllegalArgumentException) {
            println("${e.message}")
        }
    }

    people.sortBy{it.age}
    println("Список работников по возрастанию возраста: ")
    for (person in people) {
        if (person is Employee)
            println("${person.name} ${person.surname}, ${person.age}; ${person.position}")
        else if (person is StakeHolder)
            println("${person.name} ${person.surname}, ${person.age}; ${person.stake}%")
    }
}