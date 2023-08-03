data class Person(val name: String, val surname: String, val age: Int)

fun main() {
    val humans = ArrayList<Person>()
    while (true) {
        print("Введите имя. ")
        print("Ввод q выведет список всех людей: ")
        val name : String = readln()
        if (name == "q")
            break
        print("Введите фамилию: ")
        val surname : String = readln()
        print("Введите возраст: ")
        val age : Int = readln().toInt()
        humans.add(Person(name, surname, age))
    }

    humans.sortBy { it.age }
    println("Список людей по возрастанию возраста: ")
    for (human in humans) {
        println("${human.name} ${human.surname}, ${human.age}")
    }
}