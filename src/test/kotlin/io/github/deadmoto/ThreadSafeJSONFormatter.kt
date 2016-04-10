package io.github.deadmoto

import gherkin.formatter.JSONFormatter

class ThreadSafeJSONFormatter(val out: Appendable) : ThreadSafeFormatterWrapper(JSONFormatter(out))