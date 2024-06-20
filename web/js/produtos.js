 function showAlert(event) {
                                                        event.preventDefault();
                                                        swal('Opa! Calma ae...', 'Você precisa estar logado para adicionar à lista de desejos!', 'error');
                                                    }
                                                    function showAlert2(event) {
                                                        event.preventDefault();
                                                        swal('Opa! Calma ae...', 'Você já tem esse produto adicionado à Lista de Desejos', 'error');
                                                    }
                                                    function showAlert8(event) {
                                                        event.preventDefault();
                                                        swal('Opa! Calma ae...', 'Você já tem esse produto adicionado à Lista de Desejos', 'success');
                                                    }
                                                    function showAlert3(event) {
                                                        event.preventDefault();
                                                        swal('Produto removido da Lista de Desejos', '', 'success').then(() => {
                                                            event.target.closest('form').submit();
                                                        });
                                                    }

                                                    function showAlert4(event) {
                                                        event.preventDefault();
                                                        swal('Produto adicionado a Lista de Desejos', '', 'success').then(() => {
                                                            event.target.closest('form').submit();
                                                        });
                                                    }

                                                    function filterProducts() {
                                                        var filter = document.getElementById("priceFilter").value;
                                                        var url = new URL(window.location.href);
                                                        url.searchParams.set('filter', filter);
                                                        window.location.href = url.toString();
                                                    }
                                                    function setFilterValue() {
                                                        var urlParams = new URLSearchParams(window.location.search);
                                                        var filter = urlParams.get('filter');
                                                        if (filter) {
                                                            document.getElementById("priceFilter").value = filter;
                                                        }
                                                    }
                                                    window.onload = setFilterValue;