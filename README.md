# OnSingleClickListener
The extension function for implementation to prevent fast double clicking on setOnClickListener.

### Implementation

```
/* Pay Button Click */
binding.payButton.setOnSingleClickListener {

  /* Proceed Payment */
  viewModel.pay()
}
```
