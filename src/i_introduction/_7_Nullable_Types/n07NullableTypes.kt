package i_introduction._7_Nullable_Types

import util.TODO
import util.doc7

fun test() {
    val s: String = "this variable cannot store null references"
    val q: String? = null

    if (q != null) q.length      // you have to check to dereference
    val i: Int? = q?.length      // null
    val j: Int = q?.length ?: 0  // 0
}

fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
) {
//    todoTask7(client, message, mailer)
    val email = client?.personalInfo?.email

    val email2 = client?.personalInfo?.email

    if (email != null && message != null) {
        mailer.sendMessage(email, message)
    }
}


class PersonalInfo (val email: String?)
class Client (val personalInfo: PersonalInfo?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

