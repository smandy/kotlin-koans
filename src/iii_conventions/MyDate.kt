package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val yc = year.compareTo(other.year)
        return if (yc != 0) {
            yc
        } else {
            val mc = month.compareTo(other.month)
            if (mc != 0) {
                mc
            } else {
                dayOfMonth.compareTo(other.dayOfMonth)
            }
        }
    }
}


operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

data class TimeIntervals( val ti : TimeInterval,
                          val n: Int)

infix operator fun TimeInterval.times( n : Int) = TimeIntervals(this, n)

operator fun MyDate.plus( ti : TimeInterval) : MyDate {
    return when (ti) {
        TimeInterval.YEAR -> copy(year = year + 1)
        TimeInterval.WEEK -> {
            tailrec fun ret(dt : MyDate, x : Int) : MyDate =
                if (x==0) dt else ret(dt.nextDay(), x - 1)
            ret(this, 7)
        }
        TimeInterval.DAY -> nextDay()
    }
}

operator fun MyDate.plus( tis : TimeIntervals) : MyDate {
    tailrec fun ret( dt : MyDate, n : Int) : MyDate =
            if (n==0) dt else ret( dt.plus(tis.ti), n - 1)
    return ret( this, tis.n)
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>

operator fun DateRange.iterator() : Iterator<MyDate> {
    var n = start
    return object : Iterator<MyDate> {
        override fun hasNext(): Boolean = n <= endInclusive
        override fun next(): MyDate {
            val ret = n
            n = n.nextDay()
            return ret
        }
    }
}

