import javax.swing.plaf.synth.SynthTableHeaderUI

interface Person {
    var name: String
    var surname: String
    var age: Int
}

data class Employee(override var name: String, override var surname: String, override var age: Int, var position: String) : Person
data class StakeHolder(override var name: String, override var surname: String, override var age: Int, var stake: Float) : Person
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
                    print("Введите долю акций: ")
                    val stake : Float
                    var input: String = readlnOrNull() ?: throw IllegalArgumentException("stake is not string")
                    if ("%" in input) {
                        input = input.replace("%", "")
                        stake = input.toFloatOrNull() ?: throw IllegalArgumentException("cast to float has failed")
                    }
                    else {
                        stake = input.toFloatOrNull() ?: throw IllegalArgumentException("cast to float has failed")
                    }
                    people.add(StakeHolder(name, surname, age, stake))
                }
            }

        }
        catch (e : IllegalArgumentException) {
            println("${e.message}")
        }
    }
    printByAscendingAge(people)

    try {
        while (true) {
            println("Чтобы отредактировать одного из людей в компании, введите 1.")
            println("Чтобы выйти из программы, введите любой другой символ (либо оставьте поле пустым).")
            val choice: String = readlnOrNull() ?: throw IllegalArgumentException("incorrect choice")
            if (choice == "1") {
                println("Введите отредактированную запись, разделяя все свойства пробелами: ")
                val inputArray = (readlnOrNull() ?: throw IllegalArgumentException("incorrect input")).split(" ").toTypedArray()

                //Check for full and correct input
                for (string in inputArray)
                    if (string == "")
                        throw Exception("incorrect input")
                inputArray[2].toIntOrNull() ?: throw Exception("age has been entered incorrectly")
                inputArray[3].toFloatOrNull() ?: throw Exception("stake has been entered incorrectly")

                var isRecordChanged : Boolean = false
                for (person in people)
                    if (person.name == inputArray[0]) {
                        if (person is Employee) {
                            person.name = inputArray[0]
                            person.surname = inputArray[1]
                            person.age = inputArray[2].toInt() ?: throw Exception("cast to int has failed")
                            person.position = inputArray[3]
                        }

                        else if (person is StakeHolder) {
                            person.name = inputArray[0]
                            person.surname = inputArray[1]
                            person.age = inputArray[2].toInt() ?: throw Exception("cast to int has failed")
                            person.stake = inputArray[3].toFloatOrNull() ?: throw Exception("cast to float has failed")
                        }
                        isRecordChanged = true
                    }
                if (!isRecordChanged)
                    throw Exception("the record has not been changed")
                printByAscendingAge(people)
            }
            else
                break
        }
    }
    catch (e : IllegalArgumentException) {
        println("${e.message}")
    }
    catch (e : Exception) {
        println("${e.message}")
    }
}

fun printByAscendingAge(people : MutableList<Person>) {
    people.sortBy{it.age}
    println("Список работников по возрастанию возраста: ")

    for (i in 0 until people.size) {
        var person = people[i]
        if (person is Employee)
            println("$i. ${person.name} ${person.surname}, ${person.age}; ${person.position}")
        else if (person is StakeHolder)
            println("$i. ${person.name} ${person.surname}, ${person.age}; ${person.stake}%")
    }
}